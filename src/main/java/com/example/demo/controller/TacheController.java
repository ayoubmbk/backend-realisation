package com.example.demo.controller;

import com.example.demo.entity.Tache;
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
    private final TacheService tacheService;
    @Autowired
    PhaseServiceImpl phaseService;




    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

        @PostMapping("/addTache")
    public ResponseEntity<Tache> createTache(@RequestBody Tache tache) {
        Tache createdTache = tacheService.createTache(tache);
        return ResponseEntity.ok(createdTache);
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
    @GetMapping("/count")
    public long getNombreTaches() {

         Long nbr=tacheService.getNombreTaches();
         return nbr;

    }
}
