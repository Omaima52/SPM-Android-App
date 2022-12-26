package com.example.spm.service;

import com.example.spm.dto.EmergencyCaseDTO;

import java.util.List;



public interface IEmergencyCaseService {

    public EmergencyCaseDTO createEmergencyCase(EmergencyCaseDTO emergencyCaseDto);
    public List<EmergencyCaseDTO> getAllEmergencyCases();
}
