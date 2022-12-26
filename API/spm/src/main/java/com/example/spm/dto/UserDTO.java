package com.example.spm.dto;

import java.util.Set;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserDTO {

    private long id;
    private String username;
    private int age;
    private Set<String> allergiesNames;
    private Set<String> medicinesNames;


}
