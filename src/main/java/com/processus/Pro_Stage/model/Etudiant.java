package com.processus.Pro_Stage.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table
public class Etudiant {

        @Id // unique+not null
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false)
        private String Nom;

        @Column(nullable = false)
        private String Prenom;

        @Column(nullable = false)
        private String CNE;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        private String CIN;

        @Column(nullable = false)
        private String Niveau;

        @Column(nullable = false)
        private String motDePasse;



        public Etudiant() {

        }

        public Etudiant(int id, String nom, String prenom, String CNE, String email, String CIN, String niveau, String motDePasse, Set<Stage> stages, Filiere filiere) {
                this.id = id;
                Nom = nom;
                Prenom = prenom;
                this.CNE = CNE;
                this.email = email;
                this.CIN = CIN;
                Niveau = niveau;
                this.motDePasse = motDePasse;
                this.stages = stages;
                this.filiere = filiere;
        }

        @JsonIdentityReference(alwaysAsId = true)
        @OneToMany(mappedBy = "etudiant")
        private Set<Stage> stages;

        @ManyToOne
        @JoinColumn(name = "filiere_id")
        private Filiere filiere;


        private String generateRandomPassword() {
                return UUID.randomUUID().toString().substring(0, 12);
        }
}
