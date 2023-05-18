package com.example.demo.service;

import com.example.demo.entity.Equipe;

import java.util.List;

public interface EquipeService {
    List<Equipe> getAllEquipes();
    Equipe getEquipeById(Long equipeId);
    Equipe createEquipe(Equipe equipe);
    Equipe updateEquipe(Long equipeId, Equipe equipe);
    void deleteEquipe(Long equipeId);
    void assignUserToEquipe(Long equipeId, String userId);
}

