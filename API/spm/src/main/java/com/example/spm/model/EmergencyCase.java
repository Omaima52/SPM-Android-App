package com.example.spm.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import org.springframework.lang.Nullable;

import lombok.*;

@Entity
@Table(name = "emergency_cases")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class EmergencyCase{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @Column(name = "name", unique = true)
    @NonNull
    private String name;

    @Column(name = "symptomes")
    private String symptomes;
    
    @Column(name = "to_do")
    private String toDo;
    
    @Column(name = "to_avoid")
    private String toAvoid;

    @Nullable
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "emergency_case_medicine",
               joinColumns = @JoinColumn(name = "emergency_case_id"),
               inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private Set<Medicine> medicines = new HashSet<>();

    public EmergencyCase(@NonNull String name, String symptomes, String toDo, String toAvoid) {
        this.name = name;
        this.symptomes = symptomes;
        this.toDo = toDo;
        this.toAvoid = toAvoid;
    }

    @Override
	public boolean equals(Object o) {

        if (!(o instanceof EmergencyCase)) { 
			return false; 
		} 

        else{  
            EmergencyCase emergencyCase = (EmergencyCase) o;
            if(this.getName().equals(emergencyCase.getName())) return true;
            else return false;
        }
    }
    
}
