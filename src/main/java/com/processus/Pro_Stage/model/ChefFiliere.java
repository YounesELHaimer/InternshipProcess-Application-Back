package com.processus.Pro_Stage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;



@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ChefFiliere {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String nom;

    @Setter
    @Column(nullable = false)
    private String prenom;

    @Setter
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    // Relation One-to-One avec Filiere
    @OneToOne(mappedBy = "chefFiliere")
    private Filiere filiere;

    // Constructors, getters, and setters

    public ChefFiliere() {
        this.motDePasse = generateRandomPassword();
    }

    public ChefFiliere(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = generateRandomPassword();
        this.email = email;
    }

    // Getters and setters


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
