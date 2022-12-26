package com.example.spm.service.implementation;

import com.example.spm.dto.AllergyDTO;
import com.example.spm.mapper.AllergyMapper;
import com.example.spm.model.Allergy;
import com.example.spm.model.Medicine;
import com.example.spm.model.User;
import com.example.spm.repository.AllergyRepository;
import com.example.spm.repository.MedicineRepository;
import com.example.spm.repository.UserRepository;
import com.example.spm.service.IAllergyService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AllergyService implements IAllergyService {

    @Autowired
    AllergyRepository allergyRepository;

    @Autowired
    AllergyMapper allergyMapper;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    UserRepository userRepository;
    
    @Transactional
    public AllergyDTO createAllergy(AllergyDTO allergyDto){

        Allergy allergy;

        if(allergyRepository.findByName(allergyDto.getName()) != null) {
            allergy = allergyRepository.findByName(allergyDto.getName());
        }

        else{
            allergy = new Allergy(allergyDto.getName());
            allergy = allergyRepository.save(allergy);
        }

        if(allergyDto.getMedicinesToAvoidNames() != null) {
            //allergy.setMedicinesToAvoid(allergyDto.getMedicinesToAvoidNames().stream().map(medicineName -> new Medicine(medicineName)).collect(Collectors.toSet()));
            for(String medicineName : allergyDto.getMedicinesToAvoidNames()){
                if(medicineRepository.findByName(medicineName) != null){
                    allergy.getMedicinesToAvoid().add(medicineRepository.findByName(medicineName));
                }
                else{
                    allergy.getMedicinesToAvoid().add(new Medicine(medicineName));
                }
            }
        }

        return allergyMapper.entityToDto(allergy);

    }

    public List<AllergyDTO> getAllAllergies(){
        
        List<Allergy> allergies = allergyRepository.findAll();

        return allergies.stream().map((allergy) -> allergyMapper.entityToDto(allergy)).collect(Collectors.toList());
    }

    @Override
    public List<AllergyDTO> getUserAllergies(String username) {
        
        User user = userRepository.findByUsername(username);

        Set<Allergy> allergies = user.getAllergies();

        return allergies.stream().map((allergy) -> allergyMapper.entityToDto(allergy)).collect(Collectors.toList());
    }

}
