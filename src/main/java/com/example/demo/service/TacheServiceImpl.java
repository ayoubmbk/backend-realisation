package com.example.demo.service;

import com.example.demo.dto.TacheDto;
import com.example.demo.entity.Phase;
import com.example.demo.entity.Projet;
import com.example.demo.entity.Tache;
import com.example.demo.entity.Utilisateur;
import com.example.demo.enumeration.Situation;
import com.example.demo.enumeration.Status;
import com.example.demo.repo.PhaseRepos;
import com.example.demo.repo.TacheRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TacheServiceImpl implements TacheService {
    private final TacheRepo tacheRepository;
    private final UserRepo userRepo;
    @Autowired

    PhaseRepos phaseRepository;
    public TacheServiceImpl(TacheRepo tacheRepository,UserRepo userRepo) {
        this.tacheRepository = tacheRepository;
        this.userRepo=userRepo;

    }


    @Override
    public Tache createTacheAndAssignToPhase(Long phaseId, Tache tache,String createdBy) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new NotFoundException("Phase not found with id: " + phaseId));
        Utilisateur utilisateur = userRepo.findById(createdBy).orElse(null);

        Projet projet = phase.getProject();
        System.out.println(projet +"aaaaa");
        if (projet == null) {
            throw new NotFoundException("Project not found for the given phase");
        }

        tache.setPhase(phase);
        tache.setProjet(projet);
        tache.setCreatedBy(utilisateur);
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

    @Override
    public long getNombreTaches() {
        return tacheRepository.count();

    }


    public TacheDto convertToDto(Tache tache) {
        TacheDto dto = new TacheDto();
        dto.setIdTache(tache.getIdTache());
        dto.setStatus(tache.getStatus());
        dto.setNivTache(tache.getNivTache());
        dto.setNomTache(tache.getNomTache());
        dto.setPhaseId(tache.getPhase().getId());
        dto.setProjetId(tache.getProjet().getProjetId());
        return dto;
    }

    @Override
    public List<Tache> getTasksByProjectId(Long projectId) {
        return tacheRepository.findTasksByProjectId(projectId);
    }

    @Override
    public List<Tache> getTasksByCreator(Utilisateur createdBy) {
        return tacheRepository.findByCreatedBy(createdBy);
    }

    @Override
    public boolean updateTaskStatus(Long taskId) {
        Optional<Tache> optionalTask = tacheRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Tache task = optionalTask.get();
            task.setSituation(Situation.Termine);
            tacheRepository.save(task);
            return true;
        }
        return false;
    }


}

