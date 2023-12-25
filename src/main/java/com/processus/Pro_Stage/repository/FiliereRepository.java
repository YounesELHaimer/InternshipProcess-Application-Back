package com.processus.Pro_Stage.repository;

import com.processus.Pro_Stage.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    // Add custom queries if needed
    @Query(nativeQuery = true, value = "SELECT niveau, " +
            "COUNT(CASE WHEN stageTrouver = true THEN 1 ELSE null END) as stages_trouves, " +
            "COUNT(CASE WHEN stageTrouver = false THEN 1 ELSE null END) as stages_non_trouves " +
            "FROM etudiant WHERE filiere_id = :filiereId GROUP BY niveau")
    List<Object[]> countEtudiantsByNiveau(@Param("filiereId") Long filiereId);
}
