package com.example.spm.model;


import java.util.HashSet;
import java.util.Set;

import org.springframework.lang.Nullable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "allergies")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Allergy {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @Column(name = "name", unique = true)
    @NonNull
    private String name;

    @Nullable
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medicine_allergy",
               joinColumns = @JoinColumn(name = "allergy_id"),
               inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private Set<Medicine> medicinesToAvoid = new HashSet<>();

    @Override
	public boolean equals(Object o) {

        if (!(o instanceof Allergy)) { 
			return false; 
		} 

        else{  
            Allergy allergy = (Allergy) o;
            if(this.getName().equals(allergy.getName())) return true;
            else return false;
        }
        
    }

}
