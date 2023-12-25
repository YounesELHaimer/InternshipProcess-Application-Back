package com.processus.Pro_Stage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sujet;

    @Column(nullable = false)
    private String organismeDaccueil;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String annee;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDeDebut;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateSoutenance = new Date("01/09/2024"); // Set to the current date as default


    public void setDateSoutenance(Date dateSoutenance) {
        this.dateSoutenance = dateSoutenance;
    }

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    // Relation Many-to-One avec Encadrant
    @ManyToOne
    @JoinColumn(name = "encadrant_id")
    private Professeur encadrant;

    // Relation Many-to-Many avec Jury
    @ManyToMany
    @JoinTable(
            name = "stage_jury",
            joinColumns = @JoinColumn(name = "stage_id"),
            inverseJoinColumns = @JoinColumn(name = "jury_id")
    )
    private Set<Professeur> jurys = new HashSet<>();

    public Stage() {
        // Default constructor
    }

    public Stage(String sujet, String organismeDaccueil, String type,String annee, Date dateDeDebut, Date dateFin, Date dateSoutenance, Etudiant etudiant, Professeur encadrant, Set<Professeur> jurys) {
        this.sujet = sujet;
        this.organismeDaccueil = organismeDaccueil;
        this.type = type;
        this.dateDeDebut = dateDeDebut;
        this.dateFin = dateFin;
        this.etudiant = etudiant;
        this.encadrant = encadrant;
        this.jurys = jurys;
        this.annee = annee;
        this.dateSoutenance = dateSoutenance;
    }

    public void setEncadrant(Professeur encadrant) {
        this.encadrant = encadrant;
    }

    public void setJurys(Set<Professeur> jurys) {
        this.jurys = jurys;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setorganismeDaccueil(String organismeDaccueil) {
        this.organismeDaccueil = organismeDaccueil;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateDeDebut(Date dateDeDebut) {
        this.dateDeDebut = dateDeDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}