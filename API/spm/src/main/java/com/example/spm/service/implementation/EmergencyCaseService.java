package com.example.spm.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spm.dto.EmergencyCaseDTO;
import com.example.spm.mapper.EmergencyCaseMapper;
import com.example.spm.model.EmergencyCase;
import com.example.spm.model.Medicine;
import com.example.spm.repository.EmergencyCaseRepository;
import com.example.spm.repository.MedicineRepository;
import com.example.spm.service.IEmergencyCaseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmergencyCaseService implements IEmergencyCaseService {

    @Autowired
    EmergencyCaseRepository emergencyCaseRepository;

    @Autowired
    EmergencyCaseMapper emergencyCaseMapper;

    MedicineRepository medicineRepository;
    
    @Transactional
    public EmergencyCaseDTO createEmergencyCase(EmergencyCaseDTO emergencyCaseDto){

        EmergencyCase emergencyCase = emergencyCaseMapper.dtoToEntity(emergencyCaseDto);
        
        emergencyCase = emergencyCaseRepository.save(emergencyCase);

        if(emergencyCase.getMedicines() != null) {
            //emergencyCase.setMedicines(emergencyCaseDto.getMedicines().stream().map(medicineName -> new Medicine(medicineName)).collect(Collectors.toSet()));
            for(String medicineName : emergencyCaseDto.getMedicines()){
                if(medicineRepository.findByName(medicineName) != null){
                    emergencyCase.getMedicines().add(medicineRepository.findByName(medicineName));
                }
                else{
                    emergencyCase.getMedicines().add(new Medicine(medicineName));
                }
            }
        }

        return emergencyCaseMapper.entityToDto(emergencyCase);

    }

    public List<EmergencyCaseDTO> getAllEmergencyCases(){
        
        List<EmergencyCase> allergies = emergencyCaseRepository.findAll();

        return allergies.stream().map((emergencyCase) -> emergencyCaseMapper.entityToDto(emergencyCase)).collect(Collectors.toList());
    } 
}