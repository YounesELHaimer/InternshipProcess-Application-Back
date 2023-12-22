package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Etudiant;
import com.processus.Pro_Stage.model.Filiere;
import com.processus.Pro_Stage.repository.EtudiantRepository;
import com.processus.Pro_Stage.repository.FiliereRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private FiliereRepository filiereRepository;

    @Override
    public Etudiant addEtudiant(Etudiant Etudiant) {
        return etudiantRepository.save(Etudiant);
    }

    @Override
    public List<Etudiant> getEtudiant() {
        return (List<Etudiant>) etudiantRepository.findAll();
    }

    @Override
    public Etudiant getEtudiantByid(int id) {
        return etudiantRepository.findById(id).get();
    }

    @Override
    public Etudiant updateEtudiant(int id, Etudiant Etudiant) {
        Etudiant Etudiant1 = etudiantRepository.findById(id).get();
        Etudiant1.setEmail(Etudiant.getEmail());
        Etudiant1.setCIN(Etudiant.getCIN());
        Etudiant1.setCNE(Etudiant.getCNE());
        Etudiant1.setNom(Etudiant.getNom());
        Etudiant1.setPrenom(Etudiant.getPrenom());


        return etudiantRepository.save(Etudiant1);
    }

    @Override
    public void deleteEtudiant(int id) {
        etudiantRepository.deleteById(id);
    }
    @Transactional
    @Override
    public void importEtudiantsFromCSV(MultipartFile file, Long filiereId) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            // Skip the header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Map CSV columns to Etudiant fields
                Etudiant etudiant = new Etudiant();
                etudiant.setNom(data[0]);
                etudiant.setPrenom(data[1]);
                etudiant.setEmail(data[2]);
                etudiant.setCNE(data[3]);
                etudiant.setCIN(data[4]);

                // Set the filière ID
                Filiere filiere = filiereRepository.findById(filiereId)
                        .orElseThrow(() -> new RuntimeException("Filière not found")); // Handle this exception according to your needs
                etudiant.setFiliere(filiere);

                // Save the etudiant to the database
                etudiantRepository.save(etudiant);
            }
        } catch (IOException e) {
            // Handle the exception or log it
            throw new RuntimeException("Error reading CSV file", e);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Error importing students", e);
        }
    }

}
