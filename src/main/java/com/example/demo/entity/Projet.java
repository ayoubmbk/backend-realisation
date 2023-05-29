package com.example.demo.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "utilisateurs")
@ToString(exclude = "utilisateurs")
@AllArgsConstructor
@NoArgsConstructor
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projetId;
    private Long numProjet;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    private  String nomProjet;
    private String descriptionProjet;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Utilisateur createdBy;

    public Utilisateur getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Utilisateur createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescriptionProjet() {
        return descriptionProjet;
    }

    public void setDescriptionProjet(String descriptionProjet) {
        this.descriptionProjet = descriptionProjet;
    }

    public Long getProjetId() {
        return projetId;
    }

    public void setProjetId(Long projetId) {
        this.projetId = projetId;
    }

    public Long getNumProjet() {
        return numProjet;
    }

    public void setNumProjet(Long numProjet) {
        this.numProjet = numProjet;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public Set<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Set<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    @ManyToMany
    @JoinTable(name = "utilisateur_projet",
            joinColumns = @JoinColumn(name = "id_projet"),
            inverseJoinColumns = @JoinColumn(name = "id_utilisateur"))
    private Set<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phase> phases = new ArrayList<>();
}
