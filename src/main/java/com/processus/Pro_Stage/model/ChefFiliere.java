package com.processus.Pro_Stage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;



@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ChefFiliere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String motDePasse;

    // Relation One-to-One avec Filiere
    @OneToOne(mappedBy = "chefFiliere")
    private Filiere filiere;

    // Constructors, getters, and setters

    public ChefFiliere() {
        this.motDePasse = generateRandomPassword();
    }

    public ChefFiliere(String nom) {
        this.nom = nom;
        this.motDePasse = generateRandomPassword();
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

    // Générer un mot de passe aléatoire
    private String generateRandomPassword() {
        // Utilisation de UUID pour générer un mot de passe aléatoire
        // Vous pouvez ajuster la longueur selon vos besoins
        return UUID.randomUUID().toString().substring(0, 12);
    }
}
