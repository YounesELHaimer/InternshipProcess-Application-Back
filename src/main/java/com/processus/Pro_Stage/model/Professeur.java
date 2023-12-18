package com.processus.Pro_Stage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;
@Getter
@Entity
public  class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomComplet;

    // Autres champs et relations communs à tous les professeurs

    // Constructors, getters, and setters


    public Professeur() {
    }

    public Professeur(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
}
