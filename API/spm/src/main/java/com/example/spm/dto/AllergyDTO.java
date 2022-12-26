package com.example.spm.dto;

import java.util.Set;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AllergyDTO {

    private long id;
    private String name;
    private Set<String> medicinesToAvoidNames;

    public AllergyDTO(String allergyNames, Set<String> medicinesNames) {
        this.name = allergyNames;
        this.medicinesToAvoidNames = medicinesNames;
    }
}