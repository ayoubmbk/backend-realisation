package com.example.demo.controller;

import com.example.demo.entity.Equipe;
import com.example.demo.service.EquipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping("/getAllEquipes")
    public ResponseEntity<List<Equipe>> getAllEquipes() {
        List<Equipe> equipes = equipeService.getAllEquipes();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipe> getEquipeById(@PathVariable("id") Long equipeId) {
        Equipe equipe = equipeService.getEquipeById(equipeId);
        return ResponseEntity.ok(equipe);
    }

    @PostMapping("/addEquipe")
    public ResponseEntity<Equipe> createEquipe(@RequestBody Equipe equipe) {
        Equipe createdEquipe = equipeService.createEquipe(equipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipe> updateEquipe(@PathVariable("id") Long equipeId, @RequestBody Equipe equipe) {
        Equipe updatedEquipe = equipeService.updateEquipe(equipeId, equipe);
        return ResponseEntity.ok(updatedEquipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable("id") Long equipeId) {
        equipeService.deleteEquipe(equipeId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{equipeId}/users/{userId}")
    public ResponseEntity<String> assignUserToEquipe(@PathVariable Long equipeId, @PathVariable String userId) {
        try {
            equipeService.assignUserToEquipe(equipeId, userId);
            return ResponseEntity.ok("User assigned to equipe successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
