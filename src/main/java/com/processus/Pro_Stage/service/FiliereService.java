package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Filiere;

import java.util.List;

public interface FiliereService {
    Filiere findById(Long id);
    // Add other methods if needed
    List<Object[]> countEtudiantsByNiveau(Long filiereId);

}
