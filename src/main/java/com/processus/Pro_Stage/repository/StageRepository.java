package com.processus.Pro_Stage.repository;

import com.processus.Pro_Stage.model.ChefFiliere;
import com.processus.Pro_Stage.model.Etudiant;
import com.processus.Pro_Stage.model.Professeur;
import com.processus.Pro_Stage.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long>{

    List<Stage> findStagesByEtudiantId(int etudiantId);
    @Query("SELECT s FROM Stage s " +
            "JOIN s.etudiant e " +
            "JOIN e.filiere f " +
            "WHERE s.annee = :year AND s.encadrant IS NULL AND f.id = :filiereId " +
            "ORDER BY s.id")
    List<Stage> findTopNStagesByYearAndUnassigned(@Param("year") String year, @Param("filiereId") Long filiereId, Pageable pageable);


    long countByAnneeAndEtudiantFiliereId(String year, long filiereId);
    long countByAnneeAndEtudiantFiliereIdAndEncadrantIsNull(String year, Long filiereId);



}
