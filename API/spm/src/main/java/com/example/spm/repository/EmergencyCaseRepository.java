package com.example.spm.repository;

import com.example.spm.model.EmergencyCase;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmergencyCaseRepository extends JpaRepository<EmergencyCase, Long> {
      
    Optional<EmergencyCase> findUserById(long id);

    List<EmergencyCase> findAll();

    EmergencyCase findByName(String emergencyCaseName);

}
