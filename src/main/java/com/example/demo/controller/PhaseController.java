package com.example.demo.controller;

import com.example.demo.entity.Phase;
import com.example.demo.service.PhaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phases")
public class PhaseController {

    private final PhaseService phaseService;

    public PhaseController(PhaseService phaseService) {
        this.phaseService = phaseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Phase>> getAllPhases() {
        List<Phase> phases = phaseService.getAllPhases();
        return ResponseEntity.ok(phases);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Phase> getPhaseById(@PathVariable Long id) {
        Phase phase = phaseService.getPhaseById(id);
        return ResponseEntity.ok(phase);
    }

    @PostMapping("/add")
    public ResponseEntity<Phase> createPhase(@RequestBody Phase phase) {
        Phase createdPhase = phaseService.createPhase(phase);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPhase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Phase> updatePhase(@PathVariable Long id, @RequestBody Phase updatedPhase) {
        Phase phase = phaseService.updatePhase(id, updatedPhase);
        return ResponseEntity.ok(phase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhase(@PathVariable Long id) {
        phaseService.deletePhase(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public long getNombreTaches() {

        Long nbr = phaseService.getNombrePhases();
        return nbr;
    }
}
