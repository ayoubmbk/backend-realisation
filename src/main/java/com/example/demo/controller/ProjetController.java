package com.example.demo.controller;

import com.example.demo.entity.Projet;
import com.example.demo.entity.Utilisateur;
import com.example.demo.service.ProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/projects")
public class ProjetController {

  @Autowired
  ProjetService projectService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Projet>> getUserProjects(@PathVariable String userId) {
        List<Projet> projects = projectService.getProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Projet>> getAllProjects() {
        List<Projet> projects = projectService.getAllProjets();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projet> getProjectById(@PathVariable Long id) {
        Optional<Projet> projectOptional = projectService.getProjecById(id);
        return projectOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*@PostMapping("/add/{createdby}")
    public ResponseEntity<Projet> createProject(@PathVariable String createdby,@RequestBody Projet project) {
        Projet createdProject = projectService.createProjet(createdby,project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }*/
    @PostMapping("/add/{Createdby}")
    @ResponseBody
    Projet addArticle(@RequestBody Projet project,@PathVariable String Createdby)
    {
        Projet projet =projectService.createProjet(project,Createdby);
        return projet;
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Projet> updateProject(@PathVariable Long id, @RequestBody Projet project) {
        Projet updatedProject = projectService.updateProjet(id, project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProjet(id);
        return ResponseEntity.noContent().build();
    }
    /*@PutMapping("/{projectId}/users/{userId}")
    public ResponseEntity<Void> assignProjectToUser(@PathVariable Long projectId, @PathVariable String userId) {
        projectService.assignUserToProject(projectId, userId);
        return ResponseEntity.ok().build();
    }*/


    @PostMapping("/{projectId}/assign-user/{userId}")
    public ResponseEntity<Void> assignUserToProject(@PathVariable Long projectId, @PathVariable String userId) {
        projectService.assignUserToProject(projectId, userId);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/creator/{creatorId}")
    public List<Projet> getProjectsByCreator(@PathVariable("creatorId") String creatorId) {
        Utilisateur createdBy = new Utilisateur(); // Create an instance of Utilisateur with the given ID
        createdBy.setId(creatorId);

        return projectService.getProjectsByCreator(createdBy);
    }
    @GetMapping("/{projectId}/taskcount")
    public int getTaskCountForProject(@PathVariable Long projectId) {
        return projectService.getTaskCountForProject(projectId);
    }

    @GetMapping("/{projectId}/tasktEncours")
    public ResponseEntity<Integer> getTaskTerminatedCountForProject(@PathVariable Long projectId) {
        int taskCount = projectService.getTaskCountEncoursForProject(projectId);
        return ResponseEntity.ok(taskCount);
    }
    @GetMapping("/{projectId}/phasecount")
    public ResponseEntity<Integer> getPhaseCountForProject(@PathVariable Long projectId) {
        int phaseCount = projectService.getPhaseCountForProject(projectId);
        return ResponseEntity.ok(phaseCount);
    }
    @GetMapping("/{projectId}/users")
    public ResponseEntity<List<Utilisateur>> getUsersByProjectId(@PathVariable Long projectId) {
        List<Utilisateur> users = projectService.getUsersByProjectId(projectId);
        return ResponseEntity.ok(users);
    }

}