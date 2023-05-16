package com.example.demo.service;

import com.example.demo.entity.Projet;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ProjetService {

    List<Projet> getAllProjets();
    Optional<Projet> getProjecById(Long id);
    Projet createProjet(Projet projet);
    Projet updateProjet(Long id, Projet projet);
    void deleteProjet(Long id);
    }

