package com.example.spm.dto;

import java.util.Set;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MedicineDTO {

    private long id;
    private String name;
    private Set<String> medicineAllergies;
    private Set<String> emergencyCases;
    
}
