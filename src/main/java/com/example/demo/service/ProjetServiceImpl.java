package com.example.demo.service;
import com.example.demo.entity.Projet;
import com.example.demo.repo.ProjetRepo;
import com.example.demo.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetServiceImpl implements ProjetService {

    @Autowired
    ProjetRepo projectRepository;


    @Override
    public List<Projet> getAllProjets() {
        return projectRepository.findAll();

    }

    @Override
    public Optional<Projet> getProjecById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Projet createProjet(Projet projet) {
        return projectRepository.save(projet);
    }

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
}
