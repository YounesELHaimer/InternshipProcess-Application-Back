package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.ChefFiliere;
import com.processus.Pro_Stage.repository.ChefFiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChefFiliereServiceImpl implements ChefFiliereService {

    private final ChefFiliereRepository chefFiliereRepository;

    @Autowired
    public ChefFiliereServiceImpl(ChefFiliereRepository chefFiliereRepository) {
        this.chefFiliereRepository = chefFiliereRepository;
    }

    @Override
    public ChefFiliere login(String nom, String motDePasse) {
        return chefFiliereRepository.findByNomAndMotDePasse(nom, motDePasse);
    }

    // Implement other methods if needed
}
