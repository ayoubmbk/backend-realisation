package com.example.demo.service;

import com.example.demo.entity.Phase;
import com.example.demo.entity.Tache;
import com.example.demo.repo.PhaseRepos;
import com.example.demo.repo.TacheRepo;
import javafx.concurrent.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service
public class PhaseServiceImpl implements PhaseService {


    @Autowired
    PhaseRepos phaseRepository;
    @Autowired
    TacheRepo tacheRepository;

    @Override
    public List<Phase> getAllPhases() {
            return phaseRepository.findAll();
        }

    @Override
    public List<Phase> getPhasesByProjectId(Long projectId) {
        return null;
    }
    @Override
    public Phase getPhaseById(Long id) {
            return phaseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Phase not found with id: " + id));
        }
    @Override
        public Phase createPhase(Phase phase) {
            return phaseRepository.save(phase);
        }
    @Override
        public Phase updatePhase(Long id, Phase updatedPhase) {
            Phase phase = getPhaseById(id);
            phase.setNom(updatedPhase.getNom());
            return phaseRepository.save(phase);
        }
    @Override
        public void deletePhase(Long id) {
            Phase phase = getPhaseById(id);
            phaseRepository.delete(phase);
        }

    @Override
    public void assignTaskToPhase(Long phaseId, Tache tache) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new EntityNotFoundException("Phase not found with id: " + phaseId));

        tache.setPhase(phase);
        tacheRepository.save(tache);
    }

    @Override
    public void RemoveTacheFromPhase(Long phaseId, Long taskId) {
        Phase phase = phaseRepository.findById(phaseId)
                .orElseThrow(() -> new EntityNotFoundException("Phase not found with id: " + phaseId));

        Tache task = tacheRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));

        if (task.getPhase() != null && task.getPhase().equals(phase)) {
            task.setPhase(null);
            tacheRepository.save(task);
        }
    }

    @Override
    public long getNombrePhases() {
        return phaseRepository.count();

    }

}







