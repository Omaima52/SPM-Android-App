package com.example.spm.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spm.dto.MedicineDTO;
import com.example.spm.model.Medicine;
import com.example.spm.repository.AllergyRepository;
import com.example.spm.repository.EmergencyCaseRepository;

import lombok.*;

@AllArgsConstructor
@Component
public class MedicineMapper {
    
    @Autowired
    AllergyRepository allergyRepository;

    @Autowired
    EmergencyCaseRepository emergencyCaseRepository;

    public Medicine dtoToEntity(MedicineDTO medicineDto){

        // Medicine medicine = new Medicine();

        // Set<Allergy> allergies = new HashSet<>();

        // Set<EmergencyCase> emergencyCases = new HashSet<>();

        // medicine.setName(medicineDto.getName());

        // if(medicineDto.getMedicineAllergies() != null){
        //     for(String allergyName : medicineDto.getMedicineAllergies()) {
        //         allergies.add(allergyRepository.findByName(allergyName));
        //     }
        // }

        // if(medicine.getEmergencyCases() != null){
        //     for(String emergencyCaseName : medicineDto.getEmergencyCases()) {
        //         emergencyCases.add(emergencyCaseRepository.findByName(emergencyCaseName));
        //     }
        // }
        
        // medicine.setAllergies(allergies);
        // medicine.setEmergencyCases(emergencyCases);

        // return medicine;

        return new Medicine(medicineDto.getName());
    }

    public MedicineDTO entityToDto(Medicine medicine){


        Set<String> allergiesNames = new HashSet<>();
        Set<String> emergencyCasesNames = new HashSet<>();

        if(medicine.getAllergies() != null){

            allergiesNames = medicine.getAllergies().stream().map((allergy) -> allergy.getName()).collect(Collectors.toSet());
        }

        if(medicine.getEmergencyCases() != null){
           
            emergencyCasesNames = medicine.getEmergencyCases().stream().map((emergencyCase) -> emergencyCase.getName()).collect(Collectors.toSet());
        }

        return new MedicineDTO(medicine.getId(),
                               medicine.getName(),
                               allergiesNames,
                               emergencyCasesNames);    
    }

}
