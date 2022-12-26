package com.example.spm.service.implementation;

import com.example.spm.dto.UserDTO;
import com.example.spm.mapper.UserMapper;
import com.example.spm.model.User;
import com.example.spm.repository.AllergyRepository;
import com.example.spm.repository.MedicineRepository;
import com.example.spm.repository.UserRepository;
import com.example.spm.service.IUserService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService implements IUserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    AllergyRepository allergyRepository;

    @Autowired
    UserMapper userMapper;

    @Transactional
    public UserDTO createOrUpdateUSer(UserDTO userDto){

        User user;

        if(userRepository.findByUsername(userDto.getUsername()) != null) {
            user = userRepository.findByUsername(userDto.getUsername());
        }

        else{
            user = userRepository.save(new User(userDto.getUsername(), userDto.getAge()));
        }

        user.setAge(userDto.getAge());

        if(userDto.getAllergiesNames() != null){
            user.getAllergies().clear();
            for(String allergyName : userDto.getAllergiesNames()){
                if(allergyRepository.findByName(allergyName) != null){
                    user.getAllergies().add(allergyRepository.findByName(allergyName));
                }
            }
        }

        if(userDto.getMedicinesNames() != null) {
            user.getMedicines().clear();
            for(String medicineName : userDto.getMedicinesNames()){
                if(medicineRepository.findByName(medicineName) != null){
                    user.getMedicines().add(medicineRepository.findByName(medicineName));
                }
            }
        }

        return userMapper.entityToDto(user);

    }

     
}
