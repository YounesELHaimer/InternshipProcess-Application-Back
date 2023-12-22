package com.processus.Pro_Stage.controller;

import com.processus.Pro_Stage.model.ChefFiliere;
import com.processus.Pro_Stage.model.Etudiant;
import com.processus.Pro_Stage.model.Filiere;
import com.processus.Pro_Stage.model.Stage;
import com.processus.Pro_Stage.service.ChefFiliereService;
import com.processus.Pro_Stage.service.EtudiantService;
import com.processus.Pro_Stage.service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ClientController {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private ChefFiliereService chefFiliereService;



    @GetMapping("/{id}")
    public Filiere getFiliereById(@PathVariable Long id) {
        return filiereService.findById(id);
    }
    @PostMapping("/login")
    public ChefFiliere login(@RequestBody ChefFiliere loginRequest) {
        return chefFiliereService.login(loginRequest.getNom(), loginRequest.getMotDePasse());
    }

    @GetMapping("/{id}/etudiants")
    public Set<Etudiant> getEtudiantsByFiliereId(@PathVariable Long id) {
        Filiere filiere = filiereService.findById(id);
        if (filiere != null) {
            return filiere.getEtudiants();
        } else {
            // Handle case when Filiere with given id is not found
            return Collections.emptySet();
        }
    }
    @GetMapping("/Etudiants")
    public List<Etudiant> getEtudiants() {
        System.out.println("Etudiants..");
        return etudiantService.getEtudiant();
    }

    @GetMapping("/Etudiant/{id}")
    public Etudiant getEtudiantById(@PathVariable("id") int id) {
        return etudiantService.getEtudiantByid(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEtudiant(@PathVariable("id") int id, @RequestBody Etudiant etudiant) {
        try {
            etudiantService.updateEtudiant(id, etudiant);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEtudiant(@PathVariable("id") int id) {
        try {
            etudiantService.deleteEtudiant(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/{filiereId}/stages")
    public ResponseEntity<?> getStagesByFiliereId(@PathVariable Long filiereId) {
        try {
            // Récupérer la filière à partir de l'ID
            Filiere filiere = filiereService.findById(filiereId);

            // Vérifier si la filière existe
            if (filiere != null) {
                // Récupérer les étudiants de la filière
                Set<Etudiant> etudiants = filiere.getEtudiants();

                // Récupérer les stages des étudiants
                List<Stage> stages = etudiants.stream()
                        .map(Etudiant::getStages)
                        .flatMap(Set::stream)
                        .collect(Collectors.toList());

                return ResponseEntity.ok(stages);
            } else {
                // Gérer le cas où la filière n'existe pas
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La filière n'existe pas.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/add/{filiereId}")
    public ResponseEntity<?> addEtudiant(@PathVariable Long filiereId, @RequestBody Etudiant etudiant) {
        try {
            // Récupérer la filière à partir de l'ID
            Filiere filiere = filiereService.findById(filiereId);

            // Vérifier si la filière existe
            if (filiere != null) {
                // Ajouter l'étudiant à la filière

                etudiant.setFiliere(filiere);
                etudiantService.addEtudiant(etudiant);

                return ResponseEntity.ok().build();
            } else {
                // Gérer le cas où la filière n'existe pas
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La filière n'existe pas.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/import/{filiereId}")
    public ResponseEntity<String> importEtudiants(@PathVariable Long filiereId, @RequestParam("file") MultipartFile file) {
        try {
            etudiantService.importEtudiantsFromCSV(file, filiereId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}