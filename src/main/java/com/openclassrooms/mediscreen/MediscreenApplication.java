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

			LocalDate p1Dob = LocalDate.of(1966, 12, 30);
			Date p1DobSql = Date.valueOf(p1Dob);

			LocalDate p2Dob = LocalDate.of(1945, 6, 24);
			Date p2DobSql = Date.valueOf(p2Dob);

			Patient p1 = new Patient("Test", "TestNone", "F", "1 Brookside St", "100-222-3333", p1DobSql);
			Patient p2 = new Patient("Test", "TestBorderline", "M", "2 High St", "200-333-4444", p2DobSql);


			patientRepository.saveAll(List.of(p1, p2));

		};
	}
}
