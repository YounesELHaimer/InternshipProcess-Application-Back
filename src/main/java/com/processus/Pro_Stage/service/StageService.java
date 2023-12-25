package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Etudiant;
import com.processus.Pro_Stage.model.Professeur;
import com.processus.Pro_Stage.model.Stage;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public interface StageService {
    public Stage addStage(Stage stage);

    public List<Stage> getStagesByEtudiantId(int id);
    long countStagesByYearAndFiliereId(String year, Long filiereId);
    long countStagesByYearAndFiliereIdAndEncadrantIsNull(String year, Long filiereId);
    void assignJuriesToStages(List<Long> juryIds, String year, Long filiereId);

    public void assignStagesToEncadrants(List<Long> encadrantIds, String year, Long filiereId) ;

    long countStagesWithNullJury(String year, Long filiereId);

    void updateStage(Long id, Stage stage);
}
