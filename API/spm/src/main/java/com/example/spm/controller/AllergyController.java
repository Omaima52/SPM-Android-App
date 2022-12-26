package com.example.spm.controller;

import com.example.spm.dto.AllergyDTO;
import com.example.spm.service.IAllergyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@AllArgsConstructor
@RestController
@RequestMapping("allergy")
public class AllergyController {
    
    @Autowired
    IAllergyService allergyService;

    @PostMapping()
    public AllergyDTO createAllergy(@RequestBody AllergyDTO allergyDto) {
        
        return allergyService.createAllergy(allergyDto);
    }

    @GetMapping()
    public List<AllergyDTO> getAll() {
        return allergyService.getAllAllergies();
    }
    
    @GetMapping("/{username}")
    public List<AllergyDTO> getUserAllergies(@PathVariable String username) {
        return allergyService.getUserAllergies(username);
    }
}
