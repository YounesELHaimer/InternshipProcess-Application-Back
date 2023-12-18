package com.processus.Pro_Stage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ChefFiliere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    // Relation One-to-One avec Filiere
    @OneToOne(mappedBy = "chefFiliere")
    private Filiere filiere;

    // Constructors, getters, and setters

    public ChefFiliere() {
        // Default constructor
    }

    public ChefFiliere(String nom) {
        this.nom = nom;
    }

    // Getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }
}