package com.example.demo.entity;

import com.example.demo.enumeration.Niveau;
import com.example.demo.enumeration.Status;
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
    private String nomTache;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_id")
    private Phase phase;
}

