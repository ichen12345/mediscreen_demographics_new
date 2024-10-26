package com.openclassrooms.mediscreen.repository;

import com.openclassrooms.mediscreen.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByFamilyAndGiven(String family, String given);
}
