package com.processus.Pro_Stage;
import com.processus.Pro_Stage.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.processus.Pro_Stage.model")
public class ProStageApplication  {


	public static void main(String[] args) {
		SpringApplication.run(ProStageApplication.class, args);
	}


}
