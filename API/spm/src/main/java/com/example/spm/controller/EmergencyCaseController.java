package com.example.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.spm.dto.EmergencyCaseDTO;
import com.example.spm.service.IEmergencyCaseService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@AllArgsConstructor
@RestController
@RequestMapping("emergencyCase")
public class EmergencyCaseController {
    
    @Autowired
    IEmergencyCaseService emergencyCaseService;

    @PostMapping()
    public EmergencyCaseDTO createEmergencyCase(@RequestBody EmergencyCaseDTO emergencyCaseDto) {
        
        return emergencyCaseService.createEmergencyCase(emergencyCaseDto);
    }

    @GetMapping()
    public List<EmergencyCaseDTO> getAll() {
        return emergencyCaseService.getAllEmergencyCases();
    }
    
}
