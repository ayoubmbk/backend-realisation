package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idEquipe;
    private String nomEquipe;
    private Long nbMembre;


    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Utilisateur> users = new ArrayList<>();

}
