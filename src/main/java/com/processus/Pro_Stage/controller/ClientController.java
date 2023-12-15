package com.processus.Pro_Stage.controller;

import com.processus.Pro_Stage.model.Etudiant;
import com.processus.Pro_Stage.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class ClientController {

    @Autowired
    private EtudiantService etudiantService;



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

    @PostMapping("/add")
    public ResponseEntity<?> addEtudiant(@RequestBody Etudiant etudiant) {
        try {
            etudiantService.addEtudiant(etudiant);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/import")
    public ResponseEntity<String> importEtudiants(@RequestParam("file") MultipartFile file) {
      try{  etudiantService.importEtudiantsFromCSV(file);
        return ResponseEntity.ok().build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    }
}