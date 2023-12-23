package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Etudiant;
import com.processus.Pro_Stage.model.Stage;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public interface StageService {
    public List<Stage> getStagesByEtudiantId(int id);
}
