package com.processus.Pro_Stage.repository;

import com.processus.Pro_Stage.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    // Ajoutez des méthodes spécifiques si nécessaire
    Optional<Professeur> findById(Long id);
    void deleteById(Long id);
}

