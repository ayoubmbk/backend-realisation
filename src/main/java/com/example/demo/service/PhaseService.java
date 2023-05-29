package com.example.demo.service;

import com.example.demo.entity.Phase;
import com.example.demo.entity.Tache;
import javafx.concurrent.Task;

import java.util.List;

public interface PhaseService {
    Phase getPhaseById(Long phaseId);

    List<Phase> getAllPhases();

    List<Phase> getPhasesByProjectId(Long projectId);

    Phase createPhase(Phase phase);

    Phase updatePhase(Long phaseId, Phase phase);

    void deletePhase(Long phaseId);

    void assignTaskToPhase(Long phaseId, Tache tache);

    void RemoveTacheFromPhase(Long phaseId, Long taskId);

    long getNombrePhases();



}
