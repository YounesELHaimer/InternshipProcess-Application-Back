package com.processus.Pro_Stage.repository;

import com.processus.Pro_Stage.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    // Add custom queries if needed
}
