package com.example.demo.repo;

import com.example.demo.entity.Projet;
import com.example.demo.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetRepo extends JpaRepository <Projet,Long>{
    List<Projet> findByUtilisateurs_Id(String userId);
    List<Projet> findByCreatedBy(Utilisateur createdBy);
    @Query("SELECT COUNT(t) FROM Tache t WHERE t.projet.projetId = :projectId")
    int getTaskCountForProject(Long projectId);



}
