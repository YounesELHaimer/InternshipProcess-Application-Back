package com.processus.Pro_Stage;

import com.processus.Pro_Stage.model.ChefFiliere;
import com.processus.Pro_Stage.repository.ChefFiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.processus.Pro_Stage.model")
public class ProStageApplication implements CommandLineRunner {

	private final ChefFiliereRepository chefFiliereRepository;

	@Autowired
	public ProStageApplication(ChefFiliereRepository chefFiliereRepository) {
		this.chefFiliereRepository = chefFiliereRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProStageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Code for creating and saving a ChefFiliere instance

		// You can add more initialization code here if needed

		// Shutdown the application when initialization is complete

	}
}
