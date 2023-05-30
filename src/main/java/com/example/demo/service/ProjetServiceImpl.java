package com.example.demo.service;
import com.example.demo.dto.TacheDto;
import com.example.demo.entity.Projet;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repo.ProjetRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Utilisateur utilisateur =userRepository.findById(Createdby).orElse(null);
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

    @Override
    public void assignProjectToUser(Long projectId, String userId) {
        Projet projet = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        Utilisateur utilisateur = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        projet.getUtilisateurs().add(utilisateur);
        utilisateur.getProjets().add(projet);
        String projetname=projet.getNomProjet();
        System.out.println(utilisateur.getEmail() +" eeeeeeeeeeeeeee");
        projectRepository.save(projet);
        userRepository.save(utilisateur);
        emailService.sendSimpleEmail(utilisateur.getEmail(),"you have been added to a project ","Dear " + utilisateur.getUsername() + ",\n\nYou have been added to the project " + projet.getNomProjet() + ".\n\nBest regards,\nThe Project Team");


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


}