package com.example.demo.entity;

import com.example.demo.enumeration.Niveau;
import com.example.demo.enumeration.Situation;
import com.example.demo.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idTache;
    @Enumerated(EnumType.STRING)
    private Status status=Status.unassigned;
    @Enumerated(EnumType.ORDINAL)
    private Niveau nivTache;
    @Enumerated(EnumType.STRING)
    private Situation situation;
    private String nomTache;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

}

