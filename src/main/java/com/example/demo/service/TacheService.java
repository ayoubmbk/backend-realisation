package com.example.demo.service;

import com.example.demo.dto.TacheDto;
import com.example.demo.entity.Tache;
import com.example.demo.entity.Utilisateur;

import java.util.List;

public interface TacheService {
    Tache createTacheAndAssignToPhase(Long phaseId, Tache tache,String createdBy);

    Tache getTacheById(Long id);

    List<Tache> getAllTaches();

    Tache updateTache(Tache tache);

    boolean updateTaskStatus(Long taskId);

    void deleteTache(Long id);

    void assignTacheToUser(Long tacheId, String userId);

    long getNombreTaches();


    TacheDto convertToDto(Tache tache);
    List<Tache> getTasksByProjectId(Long projectId);

     List<Tache> getTasksByCreator(Utilisateur createdBy);

     int getNombreTachesTermineesByCreatedBy(String createdBy) ;


}


