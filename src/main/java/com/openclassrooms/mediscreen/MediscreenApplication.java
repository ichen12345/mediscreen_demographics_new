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
	@Autowired
	PatientRepository patientRepository;

	// Run this if app.db.init.enabled = true
	@Bean
	@ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
	public CommandLineRunner demoCommandLineRunner() {
		return args -> {

			System.out.println("Running.....");

			LocalDate p1Dob = LocalDate.of(2020, 1, 8);
			Date p1DobSql = Date.valueOf(p1Dob);

			LocalDate p2Dob = LocalDate.of(1990, 9, 3);
			Date p2DobSql = Date.valueOf(p2Dob);

			Patient p1 = new Patient("Lily", "Chen", "f", "123 Apple Street", "11233258", p1DobSql, "sth");
			Patient p2 = new Patient("Anya", "Chen", "f", "123 Orange Street", "11233258", p2DobSql, "");


			patientRepository.saveAll(List.of(p1, p2));

		};
	}
}
