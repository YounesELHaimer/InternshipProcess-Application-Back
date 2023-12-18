package com.processus.Pro_Stage.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sujet;

    @Column(nullable = false)
    private String organismeDacceil;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDeDebut;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFin;

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

    public Stage(String sujet, String organismeDacceil, String type, Date dateDeDebut, Date dateFin, Etudiant etudiant, Professeur encadrant, Set<Professeur> jurys) {
        this.sujet = sujet;
        this.organismeDacceil = organismeDacceil;
        this.type = type;
        this.dateDeDebut = dateDeDebut;
        this.dateFin = dateFin;
        this.etudiant = etudiant;
        this.encadrant = encadrant;
        this.jurys = jurys;
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

    public void setOrganismeDacceil(String organismeDacceil) {
        this.organismeDacceil = organismeDacceil;
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