package com.example.spm.dto;

import java.util.Set;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EmergencyCaseDTO {

    private long id;
    private String name;
    private String symptomes;
    private String toDo;
    private String toAvoid;
    private Set<String> medicines;
    
}

