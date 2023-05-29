package com.example.demo.repo;

import com.example.demo.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepo extends JpaRepository<Tache,Long> {

}
