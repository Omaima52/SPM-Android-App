package com.example.spm.service;

import com.example.spm.dto.AllergyDTO;

import java.util.List;


public interface IAllergyService {
    
    public AllergyDTO createAllergy(AllergyDTO allergyDto);
    public List<AllergyDTO> getAllAllergies();
    public List<AllergyDTO> getUserAllergies(String username);
}
