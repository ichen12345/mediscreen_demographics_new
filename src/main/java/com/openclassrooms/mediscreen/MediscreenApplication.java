package com.openclassrooms.mediscreen;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MediscreenApplication {

	private static final Logger log = LoggerFactory.getLogger(MediscreenApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MediscreenApplication.class, args);
	}

}
