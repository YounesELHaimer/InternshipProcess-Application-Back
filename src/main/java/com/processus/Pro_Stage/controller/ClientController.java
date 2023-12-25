package com.processus.Pro_Stage.controller;

import com.processus.Pro_Stage.model.*;
import com.processus.Pro_Stage.model.Stage;
import com.processus.Pro_Stage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private StageService stageService;



    @GetMapping("/{id}")
    public Filiere getFiliereById(@PathVariable Long id) {
        return filiereService.findById(id);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ChefFiliere loginRequest) {
        try {
            ChefFiliere chefFiliere = chefFiliereService.login(loginRequest.getEmail(), loginRequest.getMotDePasse());
            return ResponseEntity.ok(chefFiliere);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed. Please check your credentials.");
        }
    }

    @PostMapping("/loginEtudiant")
    public Etudiant loginEtudiant(@RequestBody Etudiant loginRequest) {
        return etudiantService.loginEtudiant(loginRequest.getEmail(), loginRequest.getMotDePasse());
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
    @GetMapping("/etudiants")
    public List<Etudiant> getEtudiants() {
        System.out.println("Etudiants..");
        return etudiantService.getEtudiant();
    }

    @GetMapping("/etudiant/{id}")
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

    @PutMapping("stage/{stageId}")
    public void updateStage(@PathVariable Long stageId, @RequestBody Stage stage) {
        stageService.updateStage(stageId, stage);
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
    @GetMapping("nombreSansStage/{filiereId}")
    public ResponseEntity<Integer> getNombreEtudiantsSansStage(@PathVariable Long filiereId) {
        int nombreEtudiantsSansStage = etudiantService.getNombreEtudiantsSansStage(filiereId);
        return ResponseEntity.ok(nombreEtudiantsSansStage);
    }

    @GetMapping("nombreAvecStage/{filiereId}")
    public ResponseEntity<Integer> getNombreEtudiantsAvecStage(@PathVariable Long filiereId) {
        int nombreEtudiantsAvecStage = etudiantService.getNombreEtudiantsAvecStage(filiereId);
        return ResponseEntity.ok(nombreEtudiantsAvecStage);
    }
    @GetMapping("stages/etudiant/{etudiantId}")
    public List<Stage> getStagesByEtudiantId(@PathVariable("etudiantId") int id) {
        return stageService.getStagesByEtudiantId(id);
    }


    @PostMapping("/add/stage/{etudiantId}")
    public ResponseEntity<?> addStage(@PathVariable int etudiantId, @RequestBody Stage stage) {
        try {
            Etudiant etudiant = etudiantService.getEtudiantByid(etudiantId);

            if (etudiant != null) {
                stage.setEtudiant(etudiant);
                stageService.addStage(stage);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'etudiant n'existe pas.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @Autowired
    private ProfesseurService professeurService;
    @GetMapping("/{filiereId}/statsByNiveau")
    public List<Object[]> countEtudiantsByNiveau(@PathVariable Long filiereId) {
        return filiereService.countEtudiantsByNiveau(filiereId);
    }
    @GetMapping("professeurs")
    public List<Professeur> getAllProfesseurs() {
        return professeurService.getAllProfesseurs();
    }


    @GetMapping("professeurs/{id}")
    public Professeur getProfesseurById(@PathVariable Long id) {
        return professeurService.getProfesseurById(id);
    }

    @PostMapping("addprofesseurs")
    public Professeur addProfesseur(@RequestBody Professeur professeur) {
        return professeurService.addProfesseur(professeur);
    }

    @PutMapping("professeurs/{id}")
    public void updateProfesseur(@PathVariable Long id, @RequestBody Professeur professeur) {
        professeurService.updateProfesseur(id, professeur);
    }

    @DeleteMapping("professeurs/{id}")
    public void deleteProfesseur(@PathVariable Long id) {
        professeurService.deleteProfesseur(id);
    }

    @PostMapping("professeurs/import")
    public void importProfesseurs(@RequestParam("file") MultipartFile file) throws IOException {
        professeurService.importProfesseurs(file);
    }

    @PostMapping("stages/assign/{filiereId}")
    public ResponseEntity<String> assignStagesToEncadrants(@PathVariable Long filiereId, @RequestParam List<Long> encadrantIds, @RequestParam String year) {
        try {
            stageService.assignStagesToEncadrants(encadrantIds, year, filiereId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/stages/count/{filiereId}")
    public ResponseEntity<Long> countStagesByYearAndFiliereId(
            @RequestParam String year,
            @PathVariable Long filiereId) {
        long count = stageService.countStagesByYearAndFiliereId(year, filiereId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/stages/count/null-encadrant/{filiereId}")
    public ResponseEntity<Long> countStagesByYearAndFiliereIdAndEncadrantIsNull(
            @RequestParam String year,
            @PathVariable Long filiereId) {
        long count = stageService.countStagesByYearAndFiliereIdAndEncadrantIsNull(year, filiereId);
        return ResponseEntity.ok(count);
    }

    // Inside StageController
    @PostMapping("/stages/assign/juries/{filiereId}")
    public ResponseEntity<String> assignJuriesToStages(
            @PathVariable Long filiereId,
            @RequestParam List<Long> juryIds,
            @RequestParam String year) {
        try {
            stageService.assignJuriesToStages(juryIds, year, filiereId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/stages/count/null-jury/{filiereId}")
    public ResponseEntity<Long> countStagesWithNullJury(
            @RequestParam String year,
            @PathVariable Long filiereId) {
        long count = stageService.countStagesWithNullJury(year, filiereId);
        return ResponseEntity.ok(count);
    }



}