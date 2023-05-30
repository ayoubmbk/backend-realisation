package com.example.demo.repo;

import com.example.demo.entity.Projet;
import com.example.demo.entity.Tache;
import javafx.concurrent.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepo extends JpaRepository<Tache,Long> {


}
