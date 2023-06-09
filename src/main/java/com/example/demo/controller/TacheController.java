package com.example.demo.controller;

import com.example.demo.dto.TacheDto;
import com.example.demo.entity.Projet;
import com.example.demo.entity.Tache;
import com.example.demo.entity.Utilisateur;
import com.example.demo.enumeration.Situation;
import com.example.demo.service.PhaseService;
import com.example.demo.service.PhaseServiceImpl;
import com.example.demo.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/taches")
public class TacheController {

    @Autowired
    PhaseServiceImpl phaseService;

    @Autowired TacheService tacheService;





    @PostMapping("add/{phaseId}/{Createdby}")
    public TacheDto createTacheAndAssignToPhaseAndUser(@PathVariable Long phaseId, @RequestBody Tache tache,@PathVariable String Createdby) {
        tache.setSituation(Situation.EnCours);
        Tache createdTache = tacheService.createTacheAndAssignToPhase(phaseId, tache,Createdby);
        return tacheService.convertToDto(createdTache);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tache> getTacheById(@PathVariable Long id) {
        Tache tache = tacheService.getTacheById(id);
        return ResponseEntity.ok(tache);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tache>> getAllTaches() {
        List<Tache> taches = tacheService.getAllTaches();
        return ResponseEntity.ok(taches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tache> updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        if (!id.equals(tache.getIdTache())) {
            throw new IllegalArgumentException("ID in URL path and Tache ID do not match");
        }

        Tache updatedTache = tacheService.updateTache(tache);
        return ResponseEntity.ok(updatedTache);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{tacheId}/users/{userId}")
    public ResponseEntity<String> assignTacheToUser(@PathVariable Long tacheId, @PathVariable String userId) {
        try {
            tacheService.assignTacheToUser(tacheId, userId);
            return ResponseEntity.ok("Tache assigned to user successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{phaseId}/tasks/{taskId}")
    public ResponseEntity<?> removeTaskFromPhase(@PathVariable Long phaseId, @PathVariable Long taskId) {
        try {
            phaseService.RemoveTacheFromPhase(phaseId, taskId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/{phaseId}/tasks/{taskId}")
    public ResponseEntity<?> assignTaskToPhase(@PathVariable Long phaseId, @PathVariable Long taskId) {
        try {
            Tache task = tacheService.getTacheById(taskId);
            phaseService.assignTaskToPhase(phaseId, task);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{projectId}/tasks")
    public List<Tache> getTasksByProject(@PathVariable Long projectId) {
        return tacheService.getTasksByProjectId(projectId);
    }

    @GetMapping("/count")
    public long getNombreTaches() {

         Long nbr=tacheService.getNombreTaches();
         return nbr;

    }

    @GetMapping("creator/{creatorId}")
    public List<Tache> getTachesByCreator(@PathVariable("creatorId") String creatorId) {
        Utilisateur createdBy = new Utilisateur(); // Create an instance of Utilisateur with the given ID
        createdBy.setId(creatorId);

        return tacheService.getTasksByCreator(createdBy);
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<String> updateTaskStatus(
            @PathVariable Long taskId
    ) {
        boolean updated = tacheService.updateTaskStatus(taskId);
        if (updated) {
            return ResponseEntity.ok("Task status updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nb-taches-termine/{createdBy}")
    public int getNombreTachesTermineesByCreatedBy(@PathVariable String createdBy) {
        return tacheService.getNombreTachesTermineesByCreatedBy(createdBy);
    }

    @PostMapping("/addTacheToCreator/{userId}")
    public ResponseEntity<Tache> addTacheWithUser(@RequestBody Tache tache, @PathVariable String userId) {
        Tache addedTache = tacheService.createTacheToCreator(userId,tache);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTache);
    }
    @PostMapping("/assignToPhase/{phaseId}")
    public ResponseEntity<Tache> assignTacheToPhase(@RequestBody Tache tache, @PathVariable Long phaseId) {
        Tache assignedTache = tacheService.assignTacheToPhase(tache, phaseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignedTache);
    }
    @PostMapping("/assignToProjet/{projetId}")
    public ResponseEntity<Tache> assignTacheToProjet(@RequestBody Tache tache, @PathVariable Long projetId) {
        Tache assignedTache = tacheService.assignTacheToProject(tache, projetId);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignedTache);
    }
}
