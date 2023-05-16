package com.example.demo;

import com.example.demo.service.KeycloakService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")

public class Test {
    @Autowired
    KeycloakService keycloakService;
    @RequestMapping("/nour")
    public void hello() {
        keycloakService.getAllUsers();

    }
}

