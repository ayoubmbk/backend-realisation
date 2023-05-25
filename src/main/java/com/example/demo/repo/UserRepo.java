package com.example.demo.repo;

import com.example.demo.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<Utilisateur,Long> {
    Optional<Utilisateur> findById(String userId);


}
