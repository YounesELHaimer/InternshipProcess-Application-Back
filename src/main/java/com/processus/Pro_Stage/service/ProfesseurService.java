package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Professeur;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfesseurService {
    List<Professeur> getAllProfesseurs();
    Professeur getProfesseurById(Long id);
    Professeur addProfesseur(Professeur professeur);
    void updateProfesseur(Long id, Professeur professeur);
    void deleteProfesseur(Long id);
    void importProfesseurs(MultipartFile file) throws IOException;
}
