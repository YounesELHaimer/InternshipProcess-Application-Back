package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Etudiant;
import com.processus.Pro_Stage.model.Professeur;
import com.processus.Pro_Stage.model.Stage;
import com.processus.Pro_Stage.repository.ProfesseurRepository;
import com.processus.Pro_Stage.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StageServiceImpl implements StageService{

    @Autowired
    private StageRepository stageRepository;
    @Override
    public List<Stage> getStagesByEtudiantId(int id){
        return stageRepository.findStagesByEtudiantId(id);
    };

    @Override
    public Stage addStage(Stage stage) {
        stage.getEtudiant().setStageTrouver(true);
        return stageRepository.save(stage);
    }

    @Autowired
    private ProfesseurRepository professeurRepository;
    @Override
    public long countStagesByYearAndFiliereId(String year, Long filiereId) {
        return stageRepository.countByAnneeAndEtudiantFiliereId(year, filiereId);
    }
    @Override
    public long countStagesByYearAndFiliereIdAndEncadrantIsNull(String year, Long filiereId) {
        return stageRepository.countByAnneeAndEtudiantFiliereIdAndEncadrantIsNull(year, filiereId);
    }

    @Override
    public void assignStagesToEncadrants(List<Long> encadrantIds, String year, Long filiereId) {
        // Get the total number of stages for the given year and filiereId
        long totalStages = stageRepository.countByAnneeAndEtudiantFiliereId(year, filiereId);

        if (totalStages == 0) {
            throw new RuntimeException("No stages found for the specified year and filiere");
        }

        // Get the number of encadrants and calculate the average number of stages per encadrant
        int numberOfEncadrants = encadrantIds.size();
        int averageStagesPerEncadrant = (int) (totalStages / numberOfEncadrants);
        int remainingStages = (int) (totalStages % numberOfEncadrants);

        // Get the list of encadrants based on IDs
        List<Professeur> encadrants = professeurRepository.findAllById(encadrantIds);

        // Assign stages to encadrants
        int startIndex = 0;
        int endIndex;

        for (Professeur encadrant : encadrants) {
            endIndex = startIndex + averageStagesPerEncadrant + (remainingStages-- > 0 ? 1 : 0);

            List<Stage> stages = stageRepository.findTopNStagesByYearAndUnassigned(year, filiereId, PageRequest.of(0, endIndex - startIndex));

            for (Stage stage : stages) {
                stage.setEncadrant(encadrant);
            }

            stageRepository.saveAll(stages);

            startIndex = endIndex;
        }
    }

    // Inside StageServiceImpl
    @Override
    public void assignJuriesToStages(List<Long> juryIds, String year, Long filiereId) {
        // Get the total number of stages for the given year and filiereId
        long totalStages = stageRepository.countByAnneeAndEtudiantFiliereId(year, filiereId);

        if (totalStages == 0) {
            throw new RuntimeException("No stages found for the specified year and filiere");
        }

        // Ensure that there are at least two jurors available
        if (juryIds.size() < 2) {
            throw new RuntimeException("Not enough jurors available for assignment");
        }

        // Shuffle the list of jurors to randomize the assignment
        Collections.shuffle(juryIds);

        // Create a list to store the stages for assignment
        List<Stage> stagesToAssign = stageRepository.findTopNStagesByYear(year, filiereId, PageRequest.of(0, (int) totalStages));

        // Identify stages that need additional jurors
        List<Stage> stagesWithLessThanTwoJurys = new ArrayList<>();
        for (Stage stage : stagesToAssign) {
            if (stage.getJurys().size() < 2) {
                stagesWithLessThanTwoJurys.add(stage);
            }
        }

        // Iterate through the stages and assign additional jurors if needed
        int jurorIndex = 0;
        for (Stage stage : stagesWithLessThanTwoJurys) {
            for (int i = stage.getJurys().size(); i < 2; i++) {
                if (jurorIndex < juryIds.size()) {
                    Long jurorId = juryIds.get(jurorIndex);
                    Professeur juror = professeurRepository.findById(jurorId)
                            .orElseThrow(() -> new RuntimeException("Juror not found"));

                    stage.getJurys().add(juror);
                    jurorIndex++;
                }
            }
        }

        // Save the stages with the assigned jurors
        stageRepository.saveAll(stagesToAssign);
    }

    @Override
    public long countStagesWithNullJury(String year, Long filiereId) {
        return stageRepository.countByAnneeAndEtudiantFiliereIdAndJurysIsNull(year, filiereId);
    }





    @Override
    public void updateStage(Long id, Stage updatedStage) {
        Optional<Stage> existingStage = stageRepository.findById(id);
        if (existingStage.isPresent()) {
            Stage stage1 = stageRepository.findById(id).get();
            stage1.setType(updatedStage.getType());
            stage1.setSujet(updatedStage.getSujet());
            stage1.setorganismeDaccueil(updatedStage.getOrganismeDaccueil());
            stage1.setAnnee(updatedStage.getAnnee());
            stage1.setDateDeDebut(updatedStage.getDateDeDebut());
            stage1.setDateFin(updatedStage.getDateFin());
            stageRepository.save(stage1);
        }
    }





}
