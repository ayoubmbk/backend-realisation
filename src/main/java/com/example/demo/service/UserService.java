package com.example.demo.service;

import com.example.demo.entity.Projet;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.security.Principal;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public Utilisateur addUser(Utilisateur user) {
        return userRepo.save(user);
    }


}
