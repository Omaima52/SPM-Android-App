package com.example.spm.controller;

import com.example.spm.dto.MedicineDTO;
import com.example.spm.service.IMedicineService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("medicine")
public class MedicineController {
    
    @Autowired
    IMedicineService medicineService;

    @PostMapping()
    public MedicineDTO createMedicine(@RequestBody MedicineDTO medicineDto) {
        
        return medicineService.createMedicine(medicineDto);
    }
    
    @GetMapping()
    public List<MedicineDTO> getAll() {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/emergencyCase/{emergencyCaseName}")
    public List<MedicineDTO> getMedicinesByEmergencyCase(@PathVariable("emergencyCaseName") String emergencyCaseName) {
        return medicineService.getMedicinesByEmergencyCase(emergencyCaseName);
    }

    @GetMapping("/emergencyCase/{emergencyCaseName}/avoid")
    public List<MedicineDTO> getMedicinesNotToUseWithEmergencyCase(@PathVariable("emergencyCaseName") String emergencyCaseName){
        return medicineService.getMedicinesNotToUseWithEmergencyCase(emergencyCaseName);
    }

    @GetMapping("/emergencyCase/{emergencyCaseName}/allowed")
    public List<MedicineDTO> getAllowedMedicinesByEmergencyCase(@PathVariable("emergencyCaseName") String emergencyCaseName){
        return medicineService.getAllowedMedicinesByEmergencyCase(emergencyCaseName);
    }

    @GetMapping("/user/{username}")
    public List<MedicineDTO> getUserAllergies(@PathVariable String username) {
        return medicineService.getUserMedicines(username);
    }
}
