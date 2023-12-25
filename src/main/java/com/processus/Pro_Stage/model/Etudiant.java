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
        private Boolean stageTrouver;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        private String CIN;

        @Column(nullable = false)
        private String Niveau;

        @Column(nullable = false)
        private String motDePasse;

        @Column(nullable = false)
        private String codeApogee;


        public Etudiant() {

        }

        public void setId(int id) {
                this.id = id;
        }

        public void setNom(String nom) {
                Nom = nom;
        }

        public void setPrenom(String prenom) {
                Prenom = prenom;
        }

        public void setCNE(String CNE) {
                this.CNE = CNE;
        }

        public void setEmail(String email) {
                this.email = email;
        }



        public void setCIN(String CIN) {
                this.CIN = CIN;
        }
        // Ajoutez la relation avec la classe Stage
        @JsonIdentityReference(alwaysAsId = true)
        @OneToMany(mappedBy = "etudiant")
        private Set<Stage> stages;
        // Ajout de la relation Many-to-One avec Filiere
        @ManyToOne
        @JoinColumn(name = "filiere_id")
        private Filiere filiere;



        public void setFiliere(Filiere filiere) {
                this.filiere = filiere;
        }

        public void setStages(Set<Stage> stages) {
                this.stages = stages;
        }


        public Etudiant(int id, String nom, String prenom, String CNE, Boolean stageTrouver, String email, String CIN, Filiere filiere , String codeApogee, String Niveau) {
                this.id = id;
                Nom = nom;
                Prenom = prenom;
                this.CNE = CNE;
                this.email = email;
                this.CIN = CIN;
                this.Niveau = Niveau;
                this.filiere = filiere;
                this.motDePasse = generateRandomPassword();
                this.codeApogee = codeApogee;
                this.stageTrouver = stageTrouver;
        }




        public void setNiveau(String niveau) {
                Niveau = niveau;
        }

        private String generateRandomPassword() {
                return UUID.randomUUID().toString().substring(0, 12);
        }
}
