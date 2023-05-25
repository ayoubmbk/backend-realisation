package com.example.demo.repo;

import com.example.demo.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepo extends JpaRepository <Projet,Long>{
    List<Projet> findByUtilisateurs_Id(String userId);


}
