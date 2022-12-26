package com.example.spm.repository;

import com.example.spm.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
      
    Optional<User> findUserById(long id);

    List<User> findAll();

    User findByUsername(String username);
}
