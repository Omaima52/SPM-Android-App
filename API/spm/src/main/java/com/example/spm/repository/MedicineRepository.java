package com.example.spm.repository;

import com.example.spm.model.Medicine;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
      
    Optional<Medicine> findUserById(long id);

    List<Medicine> findAll();

    Medicine findByName(String name);

    //List<Medicine> findByEmergencyCaseId(long emergencyCaseId);

}
