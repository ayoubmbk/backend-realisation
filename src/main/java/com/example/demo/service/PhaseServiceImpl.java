package com.example.demo.service;

import com.example.demo.entity.Phase;
import com.example.demo.entity.Projet;
import com.example.demo.entity.Tache;
import com.example.demo.enumeration.Situation;
import com.example.demo.enumeration.Status;
import com.example.demo.repo.PhaseRepos;
import com.example.demo.repo.ProjetRepo;
import com.example.demo.repo.TacheRepo;
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
    @Autowired
    ProjetRepo projectRepository;

    @Override
    public List<Phase> getAllPhases() {
            return phaseRepository.findAll();
        }

    @Override
    public List<Phase> getPhasesByProjectId(Long projectId) {
        return phaseRepository.findByProjectProjetId(projectId);
    }


    @Override
    public Phase getPhaseById(Long id) {
            return phaseRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Phase not found with id: " + id));
        }
    @Override
        public Phase createPhase(Phase phase,Long projectId) {

            Projet project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new EntityNotFoundException("Project not found with ID: " + projectId));

            phase.setProject(project);
            Phase createdPhase = phaseRepository.save(phase);

            return createdPhase;
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


        @Override
        public int calculatePhaseProgression(Long phaseId) {
            Phase phase = getPhaseById(phaseId);
            List<Tache> tasks = phase.getTasks();

            int totalTasks = tasks.size();
            int completedTasks = 0;

            for (Tache task : tasks) {
                if (task.getStatus() == Status.unassigned) {
                    completedTasks++;
                }
            }

            if (totalTasks == 0) {
                return 0; // To handle division by zero error
            }

            return (completedTasks * 100) / totalTasks;
        }

    @Override
    public int getNumberOfTasksInProject(Long projectId) {
        return 0;
    }

    @Override
    public List<Tache> getTasksByPhaseId(Long phaseId) {
        Phase phase = phaseRepository.findById(phaseId).orElseThrow(EntityNotFoundException::new);


        return phase.getTasks();
    }

    @Override
    public double calculatePhaseProgress(Long phaseId) {
        Phase phase = getPhaseById(phaseId);
        int totalTasks = phase.getTasks().size();
        int completedTasks = 0;

        for (Tache task : phase.getTasks()) {
            if (task.getSituation() == Situation.Termine) {
                completedTasks++;
            }
        }

        if (totalTasks > 0) {
            return (double) completedTasks / totalTasks * 100;
        } else {
            return 0;
        }
    }


}







