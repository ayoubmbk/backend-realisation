package com.example.demo.repo;

import com.example.demo.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Utilisateur,Long> {
}
