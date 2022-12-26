package com.example.spm.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.example.spm.dto.AllergyDTO;
import com.example.spm.model.Allergy;
import com.example.spm.repository.MedicineRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class AllergyMapper {

    @Autowired
    MedicineRepository medicineRepository;

    public Allergy dtoToEntity(AllergyDTO allergyDto){

        // Allergy allergy = new Allergy();

        // Set<Medicine> medicinesToAvoid = new HashSet<>();

        // allergy.setName(allergyDto.getName());

        // if(allergyDto.getMedicinesToAvoidNames() != null){
        //     for(String medicineName : allergyDto.getMedicinesToAvoidNames()){
        //         medicinesToAvoid.add(medicineRepository.findByName(medicineName));
        //     }
        // }

        // allergy.setMedicinesToAvoid(medicinesToAvoid);

        // return allergy;

        return new Allergy(allergyDto.getName());

    }

    public AllergyDTO entityToDto(Allergy allergy){
        
        // AllergyDTO allergyDTO = new AllergyDTO();

        

        // allergy.setName(allergy.getName());

        // if(allergy.getMedicinesToAvoid() != null)
        // for(Medicine medicine : allergy.getMedicinesToAvoid()){
        //     medicinesToAvoidNames.add(medicine.getName());
        // }

        // allergyDTO.setMedicinesToAvoidNames(medicinesToAvoidNames);


        // return allergyDTO;

        Set<String> medicinesToAvoidNames = new HashSet<>();

        if(allergy.getMedicinesToAvoid() != null){
            medicinesToAvoidNames = allergy.getMedicinesToAvoid().stream().map((medicine) -> medicine.getName()).collect(Collectors.toSet());
        }

        return new AllergyDTO(allergy.getId(),
                              allergy.getName(),
                              medicinesToAvoidNames);
    }
}
