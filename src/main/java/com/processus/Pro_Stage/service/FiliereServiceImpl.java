package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Filiere;
import com.processus.Pro_Stage.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiliereServiceImpl implements FiliereService {

    private final FiliereRepository filiereRepository;

    @Autowired
    public FiliereServiceImpl(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    @Override
    public Filiere findById(Long id) {
        return filiereRepository.findById(id).orElse(null);
    }

    // Implement other methods if needed
    @Override
    public List<Object[]> countEtudiantsByNiveau(Long filiereId) {
        return filiereRepository.countEtudiantsByNiveau(filiereId);
    }
}
