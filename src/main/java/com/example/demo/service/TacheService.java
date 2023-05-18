package com.example.demo.service;

import com.example.demo.entity.Tache;

import java.util.List;

public interface TacheService {
    Tache createTache(Tache tache);
    Tache getTacheById(Long id);
    List<Tache> getAllTaches();
    Tache updateTache(Tache tache);
    void deleteTache(Long id);

    void assignTacheToUser(Long tacheId, String userId);

}
