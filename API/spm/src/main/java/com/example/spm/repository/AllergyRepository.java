package com.example.spm.repository;

import com.example.spm.model.Allergy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
      
    Optional<Allergy> findUserById(long id);

    List<Allergy> findAll();

    Allergy findByName(String name);
}
