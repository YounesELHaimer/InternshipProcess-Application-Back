package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Etudiant;
import com.processus.Pro_Stage.repository.EtudiantRepository;
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
    @Override
    public void importEtudiantsFromCSV(MultipartFile file) {
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

                // Save the etudiant to the database
                etudiantRepository.save(etudiant);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your requirements
        }
    }
}
