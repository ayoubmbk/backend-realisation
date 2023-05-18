package com.example.demo.repo;

import com.example.demo.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacheRepo extends JpaRepository<Tache,Long> {

}
