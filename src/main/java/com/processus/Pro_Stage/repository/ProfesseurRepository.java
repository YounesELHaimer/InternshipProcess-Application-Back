package com.processus.Pro_Stage.repository;

import com.processus.Pro_Stage.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
}

