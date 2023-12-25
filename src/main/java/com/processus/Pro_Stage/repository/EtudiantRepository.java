package com.processus.Pro_Stage.repository;

import com.processus.Pro_Stage.model.ChefFiliere;
import com.processus.Pro_Stage.model.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends CrudRepository<Etudiant,Integer> {
    Etudiant findEtudiantByEmailAndMotDePasse(String email, String motDePasse);
    Etudiant findEtudiantById(int id);
    List<Etudiant> findByFiliereIdAndStageTrouver(Long filiereId, boolean stageTrouver);


}
