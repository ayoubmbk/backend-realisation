package com.example.demo.service;

import com.example.demo.entity.Equipe;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repo.EquipeRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
@Service
public class EquipeServiceImpl implements EquipeService
{

    @Autowired
    EquipeRepo equipeRepository;
    @Autowired
    UserRepo userRepo;


    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Equipe getEquipeById(Long equipeId) {
        return equipeRepository.findById(equipeId)
                .orElseThrow(() -> new EntityNotFoundException("Equipe not found"));
    }

    @Override
    public Equipe createEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public Equipe updateEquipe(Long equipeId, Equipe equipe) {
        Equipe existingEquipe = getEquipeById(equipeId);
        existingEquipe.setNomEquipe(equipe.getNomEquipe());
        // Set other attributes

        return equipeRepository.save(existingEquipe);
    }

    @Override
    public void deleteEquipe(Long equipeId) {
        equipeRepository.deleteById(equipeId);
    }

    @Override
    public void assignUserToEquipe(Long equipeId, String userId) {
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new EntityNotFoundException("Equipe not found"));
        Utilisateur user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        equipe.getUsers().add(user);
        equipeRepository.save(equipe);
    }
}

