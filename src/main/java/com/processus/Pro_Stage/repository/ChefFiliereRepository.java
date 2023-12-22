package com.processus.Pro_Stage.repository;



import com.processus.Pro_Stage.model.ChefFiliere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChefFiliereRepository extends JpaRepository<ChefFiliere, Long> {
    ChefFiliere findByNomAndMotDePasse(String nom, String motDePasse);
}