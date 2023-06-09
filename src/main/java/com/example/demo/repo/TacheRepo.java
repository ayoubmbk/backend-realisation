package com.example.demo.repo;

import com.example.demo.entity.Projet;
import com.example.demo.entity.Tache;
import com.example.demo.entity.Utilisateur;
import com.example.demo.enumeration.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepo extends JpaRepository<Tache,Long> {
    @Query("SELECT t FROM Tache t WHERE t.projet.projetId = :projectId")
    List<Tache> findTasksByProjectId(@Param("projectId") Long projectId);
    //List<Tache> findTasksBySituationAndUser_Id(Situation situation, Long userId);
    //List<Tache> findByCreatedBy_IdOrUserId(String createdById, String userId);
    List<Tache> findByCreatedBy(Utilisateur createdBy);
    int countByUserAndSituation(Utilisateur user, Situation situation);
    int countBySituationAndCreatedBy(Situation situation, Utilisateur createdBy);
}


