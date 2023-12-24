package com.processus.Pro_Stage.service;

import com.processus.Pro_Stage.model.Professeur;
import com.processus.Pro_Stage.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesseurServiceImpl implements ProfesseurService {
    private final ProfesseurRepository professeurRepository;

    public ProfesseurServiceImpl(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    @Override
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    @Override
    public Professeur getProfesseurById(Long id) {
        Optional<Professeur> professeur = professeurRepository.findById(id);
        return professeur.orElse(null);
    }

    @Override
    public Professeur addProfesseur(Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    @Override
    public void updateProfesseur(Long id, Professeur updatedProfesseur) {
        Optional<Professeur> existingProfesseur = professeurRepository.findById(id);
        if (existingProfesseur.isPresent()) {
            updatedProfesseur.setId(id);
            professeurRepository.save(updatedProfesseur);
        }
    }

    @Override
    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }

    @Override
    public void importProfesseurs(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            // Skip the header row
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Map CSV columns to Professeur fields
                Professeur professeur = new Professeur();
                professeur.setNom(data[0]);
                professeur.setPrenom(data[1]);
                professeur.setEmail(data[2]);
                professeur.setDepartement(data[3]);

                // Save the professeur to the database
                professeurRepository.save(professeur);
            }
        } catch (IOException e) {
            // Handle the exception or log it
            throw new RuntimeException("Error reading CSV file", e);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Error importing professors", e);
        }
    }
}