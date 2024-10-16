package com.openclassrooms.mediscreen.service;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.repository.PatientRepository;

import java.util.List;

public interface PatientService {
    Patient addPatient(Patient patient);

    List<Patient> findAll();

    Patient updatePatient(Patient patient);

    void deletePatient(Long id);

    Patient findAPatient(Long id);
}
