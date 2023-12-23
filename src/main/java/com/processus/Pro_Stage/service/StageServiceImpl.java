package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Stage;
import com.processus.Pro_Stage.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StageServiceImpl implements StageService{

    @Autowired
    private StageRepository stageRepository;

    public List<Stage> getStagesByEtudiantId(int id){
        return stageRepository.findStagesByEtudiantId(id);
    };

}
