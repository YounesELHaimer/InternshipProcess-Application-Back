package com.processus.Pro_Stage.repository;

import com.processus.Pro_Stage.model.ChefFiliere;
import com.processus.Pro_Stage.model.Etudiant;
import com.processus.Pro_Stage.model.Professeur;
import com.processus.Pro_Stage.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long>{

    List<Stage> findAllStagesByEtudiantId(int etudiantId);

}
