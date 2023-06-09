package com.example.demo.service;

import com.example.demo.entity.Projet;
import com.example.demo.entity.Utilisateur;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ProjetService {

    List<Projet> getAllProjets();
    Optional<Projet> getProjecById(Long id);
    Projet createProjet(Projet projet,String Createdby);

    /*Projet createProjet(Projet projet);*/

    Projet updateProjet(Long id, Projet projet);
    void deleteProjet(Long id);
    //void assignProjectToUser(Long projectId, String userId);
    public List<Projet> getProjectsByUserId(String userId);

    void assignUserToProject(Long projectId, String userId);
    public void assignUserToProject(String userId, Long projectId);
    public List<Projet> getProjectsByCreator(Utilisateur createdBy);
    int getTaskCountForProject(Long projectId);
   int getTaskCountEncoursForProject(Long projectId) ;
   int getPhaseCountForProject(Long projectId) ;




}

