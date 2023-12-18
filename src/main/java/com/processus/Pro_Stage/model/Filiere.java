package com.processus.Pro_Stage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Relation One-to-Many avec Etudiant
    @OneToMany(mappedBy = "filiere")
    private Set<Etudiant> etudiants;

    // Relation One-to-One avec ChefFiliere
    @OneToOne
    @JoinColumn(name = "chef_filiere_id")
    private ChefFiliere chefFiliere;

    // Constructors, getters, and setters

    public Filiere() {
        // Default constructor
    }

    public Filiere(String name) {
        this.name = name;
    }

    public Filiere(String name, ChefFiliere chefFiliere) {
        this.name = name;
        this.chefFiliere = chefFiliere;
    }

    // Getters and setters

    public void setChefFiliere(ChefFiliere chefFiliere) {
        this.chefFiliere = chefFiliere;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }
}