package com.example.demo.controller;

import com.example.demo.entity.Utilisateur;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Utilisateur> createProject(@RequestBody Utilisateur user) {
        Utilisateur  createdProject = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }
}
