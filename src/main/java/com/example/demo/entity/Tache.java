package com.example.demo.entity;

import com.example.demo.enumeration.Niveau;
import com.example.demo.enumeration.Situation;
import com.example.demo.enumeration.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @Temporal(TemporalType.TIMESTAMP)
    private  Date dateEchanche;

    public Long getIdTache() {
        return idTache;
    }

    public void setIdTache(Long idTache) {
        this.idTache = idTache;
    }

    public Date getDateEchanche() {
        return dateEchanche;
    }

    public void setDateEchanche(Date dateEchanche) {
        this.dateEchanche = dateEchanche;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur user;


    @ManyToOne
    @JoinColumn(name = "created_by")
    private Utilisateur createdBy;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "phase_id")
    private Phase phase;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

}

