package com.example.spm.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

import org.springframework.lang.Nullable;

import lombok.*;



@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class User{

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @Column(name = "username", unique = true)
    @NonNull
	private String username;

    @Column(name = "age")
    @NonNull
    private int age;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Allergy> allergies = new HashSet<>();

    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Medicine> medicines = new HashSet<>();

    public User(@NonNull String username, Set<Allergy> allergies, Set<Medicine> medicines) {
        this.username = username;
        this.allergies = allergies;
        this.medicines = medicines;
    }

    
}