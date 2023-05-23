package com.example.demo.service;

import com.example.demo.entity.Tache;
import com.example.demo.entity.Utilisateur;
import com.example.demo.enumeration.Status;
import com.example.demo.repo.TacheRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TacheServiceImpl implements TacheService {
    private final TacheRepo tacheRepository;
    private final UserRepo userRepo;

    public TacheServiceImpl(TacheRepo tacheRepository,UserRepo userRepo) {
        this.tacheRepository = tacheRepository;
        this.userRepo=userRepo;
    }

    @Override
    public Tache createTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    @Override
    public Tache getTacheById(Long id) {
        return tacheRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tache not found"));
    }

    @Override
    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    @Override
    public Tache updateTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    @Override
    public void deleteTache(Long id) {
        tacheRepository.deleteById(id);
    }

    @Override
    public void assignTacheToUser(Long tacheId, String userId) {
        Tache tache = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new EntityNotFoundException("Tache not found"));

        Utilisateur user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        tache.setUser(user);
        tache.setStatus(Status.assgined); // Update the task status to ASSIGNED

        tacheRepository.save(tache);
    }
}

