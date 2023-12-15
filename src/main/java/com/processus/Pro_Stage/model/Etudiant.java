package com.processus.Pro_Stage.model;

import jakarta.persistence.*;

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

        public Etudiant() {

        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getNom() {
                return Nom;
        }

        public void setNom(String nom) {
                Nom = nom;
        }

        public String getPrenom() {
                return Prenom;
        }

        public void setPrenom(String prenom) {
                Prenom = prenom;
        }

        public String getCNE() {
                return CNE;
        }

        public void setCNE(String CNE) {
                this.CNE = CNE;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getCIN() {
                return CIN;
        }

        public void setCIN(String CIN) {
                this.CIN = CIN;
        }

        public Etudiant(int id, String nom, String prenom, String CNE, String email, String CIN) {
                this.id = id;
                Nom = nom;
                Prenom = prenom;
                this.CNE = CNE;
                this.email = email;
                this.CIN = CIN;
        }
}
