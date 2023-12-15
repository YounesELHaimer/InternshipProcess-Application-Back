package com.processus.Pro_Stage.repository;

import com.processus.Pro_Stage.model.Etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends CrudRepository<Etudiant,Integer> {
}
