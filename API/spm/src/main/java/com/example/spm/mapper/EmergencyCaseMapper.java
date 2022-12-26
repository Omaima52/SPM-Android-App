package com.example.spm.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spm.dto.EmergencyCaseDTO;
import com.example.spm.model.EmergencyCase;
import com.example.spm.repository.MedicineRepository;

import lombok.*;

@AllArgsConstructor
@Component
public class EmergencyCaseMapper {
    
    @Autowired
    MedicineRepository medicineRepository;

    public EmergencyCase dtoToEntity(EmergencyCaseDTO emergencyCaseDto){

        return new EmergencyCase(emergencyCaseDto.getName(), emergencyCaseDto.getSymptomes(), emergencyCaseDto.getToDo(), emergencyCaseDto.getToAvoid());
    }

    public EmergencyCaseDTO entityToDto(EmergencyCase emergencyCase){

        Set<String> medicinesNames = new HashSet<>();

        if(emergencyCase.getMedicines() != null){
            medicinesNames = emergencyCase.getMedicines().stream().map((medicine)-> medicine.getName()).collect(Collectors.toSet());
        }

        return new EmergencyCaseDTO(emergencyCase.getId(),
                                    emergencyCase.getName(),
                                    emergencyCase.getSymptomes(),
                                    emergencyCase.getToDo(), 
                                    emergencyCase.getToAvoid(),
                                    medicinesNames);
    }


}
