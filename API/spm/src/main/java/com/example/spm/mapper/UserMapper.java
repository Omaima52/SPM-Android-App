package com.example.spm.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spm.dto.UserDTO;
import com.example.spm.model.User;
import com.example.spm.repository.AllergyRepository;
import com.example.spm.repository.MedicineRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UserMapper {

    @Autowired
    AllergyRepository allergyRepository;

    @Autowired
    MedicineRepository medicineRepository;
    
    public User dtoToEntity(UserDTO userDto) {

        // User user = new User();

        // Set<Allergy> allergies = new HashSet<>();
        // Set<Medicine> medicines = new HashSet<>();

        // user.setFirstName(userDto.getFirstName());
        // user.setLastName(userDto.getLastName());
        // user.setAge(userDto.getAge());

        // if(userDto.getAllergiesNames() != null){
        //     for(String allergyName : userDto.getAllergiesNames()){
        //     allergies.add(allergyRepository.findByName(allergyName));
        // }
        // }
        
        // if(userDto.getMedicinesNames() != null){
        //     for(String medicineName : userDto.getMedicinesNames()){
        //     medicines.add(medicineRepository.findByName(medicineName));
        // }
        // }
        
        // user.setAllergies(allergies);
        // user.setMedicines(medicines);

        // return user;

        return new User(userDto.getUsername(), userDto.getAge());
    }

    public UserDTO entityToDto(User user) {

        // UserDTO userDto = new UserDTO();

        

        // userDto.setFirstName(user.getFirstName());
        // userDto.setLastName(user.getLastName());
        // userDto.setAge(user.getAge());

        // for(Allergy allergy : user.getAllergies()){
        //     allergiesNames.add(allergy.getName());
        // }

        // for(Medicine medicine : user.getMedicines()){
        //     medicinesNames.add(medicine.getName());
        // }

        // userDto.setAllergiesNames(allergiesNames);
        // userDto.setMedicinesNames(medicinesNames);

        // return userDto;

        Set<String> allergiesNames = new HashSet<>();
        Set<String> medicinesNames = new HashSet<>();

        if(user.getAllergies() != null){
            allergiesNames = user.getAllergies().stream().map((allergy) -> allergy.getName()).collect(Collectors.toSet());
        }

        if(user.getMedicines() != null){
            medicinesNames = user.getMedicines().stream().map((medicine)-> medicine.getName()).collect(Collectors.toSet());
        }

        return new UserDTO(user.getId(),
                           user.getUsername(),
                           user.getAge(),
                           allergiesNames,
                           medicinesNames);    
    }

}
