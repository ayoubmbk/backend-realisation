package com.example.demo.service;
import com.example.demo.dto.TacheDto;
import com.example.demo.entity.Phase;
import com.example.demo.entity.Projet;
import com.example.demo.entity.Utilisateur;
import com.example.demo.enumeration.Situation;
import com.example.demo.repo.ProjetRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjetServiceImpl implements ProjetService {

    @Autowired
    ProjetRepo projectRepository;
    @Autowired
    UserRepo userRepository;
    @Autowired
    private EmailSenderService emailService;


    @Override
    public List<Projet> getAllProjets() {
        return projectRepository.findAll();

    }

    @Override
    public Optional<Projet> getProjecById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Projet createProjet(Projet projet, String Createdby) {
        Utilisateur utilisateur = userRepository.findById(Createdby).orElse(null);
        projet.setCreatedBy(utilisateur);
        return projectRepository.save(projet);
    }



    /*@Override
    public Projet createProjet(Projet projet) {
        return projectRepository.save(projet);

    }*/

    @Override
    public Projet updateProjet(Long id, Projet project) {
        Optional<Projet> existingProjectOptional = projectRepository.findById(id);
        if (existingProjectOptional.isPresent()) {
            Projet existingProject = existingProjectOptional.get();
            existingProject.setNomProjet(project.getNomProjet());
            // Update other fields as needed
            return projectRepository.save(existingProject);
        } else {
            throw new IllegalArgumentException("Project not found with id: " + id);
        }
    }

    @Override
    public void deleteProjet(Long id) {
        projectRepository.deleteById(id);

    }



    public List<Projet> getProjectsByUserId(String userId) {
        return projectRepository.findByUtilisateurs_Id(userId);
    }

    public void assignUserToProject(Long projectId, String userId) {
        Projet projet = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Utilisateur utilisateur = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        projet.getUtilisateurs().add(utilisateur);
        projectRepository.save(projet);


    }

    public List<Projet> getProjectsByCreator(Utilisateur createdBy) {
        return projectRepository.findByCreatedBy(createdBy);
    }

    @Override
    public int getTaskCountForProject(Long projectId) {
        return projectRepository.getTaskCountForProject(projectId);
    }

    @Override
    public int getTaskCountEncoursForProject(Long projectId) {
        Projet projet = projectRepository.findById(projectId).orElse(null);
        if (projet == null) {
            throw new IllegalArgumentException("Invalid project ID");
        }
        return projet.getPhases().stream()
                .flatMap(phase -> phase.getTasks().stream())
                .filter(tache -> tache.getSituation() == Situation.EnCours)
                .toArray().length;


    }

    @Override
    public List<Utilisateur> getUsersByProjectId(Long projectId) {
        return null;
    }

    @Override
    public int getPhaseCountForProject(Long projectId) {
        Optional<Projet> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            Projet project = projectOptional.get();
            List<Phase> phases = project.getPhases();
            return phases.size();
        }
        return 0;
    }

    @Override
    public void assignUserToProject(String userId, Long projectId) {
        Utilisateur user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Projet project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with ID: " + projectId));

        project.getUtilisateurs().add(user);
        user.getProjets().add(project);

        projectRepository.save(project);
        userRepository.save(user);


}
}