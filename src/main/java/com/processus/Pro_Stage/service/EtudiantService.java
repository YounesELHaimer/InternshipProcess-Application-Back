package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.ChefFiliere;
import com.processus.Pro_Stage.model.Etudiant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface EtudiantService {
    Etudiant loginEtudiant(String email, String motDePasse);
    public Etudiant addEtudiant(Etudiant etudiant);

    public List<Etudiant> getEtudiant();

    public Etudiant getEtudiantByid(int id);

    public Etudiant updateEtudiant(int id , Etudiant etudiant);

    public void deleteEtudiant(int id);
    void importEtudiantsFromCSV(MultipartFile file , Long filiereId);
}
