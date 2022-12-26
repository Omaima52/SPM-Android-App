package com.example.spm.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import org.springframework.lang.Nullable;

import lombok.*;


@Entity
@Table(name = "medicines")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class Medicine {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @Column(name = "name", unique = true)
    @NonNull
    private String name;

    @Nullable
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medicine_allergy",
               joinColumns = @JoinColumn(name = "medicine_id"),
               inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    private Set<Allergy> allergies = new HashSet<>();

    @Nullable
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "emergency_case_medicine",
               joinColumns = @JoinColumn(name = "medicine_id"),
               inverseJoinColumns = @JoinColumn(name = "emergency_case_id"))
    private Set<EmergencyCase> emergencyCases = new HashSet<>();

    @Override
	public boolean equals(Object o) {

        if (!(o instanceof Medicine)) { 
			return false; 
		} 

        else{  
            Medicine medicine = (Medicine) o;
            if(this.getName().equals(medicine.getName())) return true;
            else return false;
        }
    }
}
