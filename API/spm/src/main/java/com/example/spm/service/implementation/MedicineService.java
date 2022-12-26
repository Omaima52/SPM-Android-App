package com.example.spm.service.implementation;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.spm.dto.MedicineDTO;
import com.example.spm.mapper.MedicineMapper;
import com.example.spm.model.Allergy;
import com.example.spm.model.EmergencyCase;
import com.example.spm.model.Medicine;
import com.example.spm.model.User;
import com.example.spm.repository.AllergyRepository;
import com.example.spm.repository.EmergencyCaseRepository;
import com.example.spm.repository.MedicineRepository;
import com.example.spm.repository.UserRepository;
import com.example.spm.service.IMedicineService;

import jakarta.transaction.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MedicineService implements IMedicineService {
    
    @Autowired
    MedicineMapper medicineMapper;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    AllergyRepository allergyRepository;

    @Autowired
    EmergencyCaseRepository emergencyCaseRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public MedicineDTO createMedicine(MedicineDTO medicineDto){

        Medicine medicine;

        if(medicineRepository.findByName(medicineDto.getName()) != null) {
            medicine = medicineRepository.findByName(medicineDto.getName());
        }

        else{
            medicine = medicineRepository.save(new Medicine(medicineDto.getName()));
        }

        if(medicineDto.getMedicineAllergies() != null) {
            //medicine.setAllergies(medicineDto.getMedicineAllergies().stream().map(allergyName -> new Allergy(allergyName)).collect(Collectors.toSet()));
            for(String allergyName : medicineDto.getMedicineAllergies()){
                if(allergyRepository.findByName(allergyName) != null){
                    medicine.getAllergies().add(allergyRepository.findByName(allergyName));
                }
                else{
                    medicine.getAllergies().add(new Allergy(allergyName));
                }
            }
        }

        if(medicineDto.getEmergencyCases() != null){
            //medicine.setEmergencyCases(medicineDto.getEmergencyCases().stream().map(emergencyCaseName -> new EmergencyCase(emergencyCaseName)).collect(Collectors.toSet()));
            for(String emergencyCaseName : medicineDto.getEmergencyCases()){
                if(emergencyCaseRepository.findByName(emergencyCaseName) != null){
                    medicine.getEmergencyCases().add(emergencyCaseRepository.findByName(emergencyCaseName));
                }
                else{
                    medicine.getEmergencyCases().add(new EmergencyCase(emergencyCaseName));
                }
            }
        }

        return medicineMapper.entityToDto(medicine);
    }

    public List<MedicineDTO> getAllMedicines(){
        
        List<Medicine> medicines = medicineRepository.findAll();
        return medicines.stream().map((medicine) -> medicineMapper.entityToDto(medicine)).collect(Collectors.toList());
    
    }

    @Override
    public List<MedicineDTO> getMedicinesByEmergencyCase(String emergencyCaseName) {
        
        // List<Medicine> medicines = medicineRepository.findByEmergencyCaseId(emergencyCaseId);
        // return medicines.stream().map((medicine) -> medicineMapper.entityToDto(medicine)).toList();

        EmergencyCase emergencyCase;
        Set<Medicine> medicines = new HashSet<>();

        try{
            emergencyCase = emergencyCaseRepository.findByName(emergencyCaseName);

            if(emergencyCase.getMedicines() != null){
                medicines = emergencyCase.getMedicines();
            }

            return medicines.stream().map((medicine) -> medicineMapper.entityToDto(medicine)).collect(Collectors.toList());

        }

        catch(Exception ex){
            System.out.println(ex.getMessage());
        } 

        return medicines.stream().map((medicine) -> medicineMapper.entityToDto(medicine)).collect(Collectors.toList());
          
    }

    @Override
    public List<MedicineDTO> getUserMedicines(String username) {
        
        User user = userRepository.findByUsername(username);

        Set<Medicine> medicines = user.getMedicines();

        return medicines.stream().map((medicine) -> medicineMapper.entityToDto(medicine)).collect(Collectors.toList());
    }

    @Override
    public List<Medicine> getMedicinesEntityByEmergencyCase(String emergencyCaseName) {
        
        // List<Medicine> medicines = medicineRepository.findByEmergencyCaseId(emergencyCaseId);
        // return medicines.stream().map((medicine) -> medicineMapper.entityToDto(medicine)).toList();

        EmergencyCase emergencyCase;
        Set<Medicine> medicines = new HashSet<>();

        try{
            emergencyCase = emergencyCaseRepository.findByName(emergencyCaseName);

            if(emergencyCase.getMedicines() != null){
                medicines = emergencyCase.getMedicines();
            }

            return new ArrayList<>(medicines);

        }

        catch(Exception ex){
            System.out.println(ex.getMessage());
        } 

        return new ArrayList<>(medicines);
          
    }

    @Transactional
    @Override
    public List<MedicineDTO> getAllowedMedicinesByEmergencyCase(String emergencyCaseName){

        List<MedicineDTO> allowedMedicinesDto = new ArrayList<>();

        List<MedicineDTO> emergencyCaseAllMedicinesDto = getMedicinesByEmergencyCase(emergencyCaseName);

        for(MedicineDTO m : emergencyCaseAllMedicinesDto){
            System.out.println("All " + m.getName());
        }
        
        List<MedicineDTO> notAllowedMedicinesDto = getMedicinesNotToUseWithEmergencyCase(emergencyCaseName);

        for(MedicineDTO m : notAllowedMedicinesDto){
            System.out.println("avoid " + m.getName());
        }

        emergencyCaseAllMedicinesDto.removeAll(notAllowedMedicinesDto);

        allowedMedicinesDto = emergencyCaseAllMedicinesDto;

        return allowedMedicinesDto;
    }


    @Transactional
    @Override
    public List<MedicineDTO> getMedicinesNotToUseWithEmergencyCase(String emergencyCaseName){

        User user = userRepository.findAll().get(0);

        List<Medicine> medicinesNotToUse = new ArrayList<>();

        List<Medicine> emergencyCaseAllMedicines = getMedicinesEntityByEmergencyCase(emergencyCaseName);

        for(Medicine medicine : emergencyCaseAllMedicines){
            for(Allergy userAllergy : user.getAllergies()){
                if(userAllergy.getMedicinesToAvoid().contains(medicine) && !medicinesNotToUse.contains(medicine)){
                    medicinesNotToUse.add(medicine);
                }
            }
        }


        return medicinesNotToUse.stream().map((medicine) -> medicineMapper.entityToDto(medicine)).collect(Collectors.toList());
    }
    
}
