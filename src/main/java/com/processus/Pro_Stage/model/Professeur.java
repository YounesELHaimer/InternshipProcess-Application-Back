package com.processus.Pro_Stage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
public  class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String departement;

    // Autres champs et relations communs à tous les professeurs

    // Constructors, getters, and setters


    public Professeur() {
    }

    public Professeur(Long id, String nom, String prenom, String email, String departement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.departement = departement;
    }
}
