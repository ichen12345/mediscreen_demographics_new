package com.openclassrooms.mediscreen.service;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        entityManager.flush(); // Force flush to the database
        return savedPatient;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Patient patient) {
        Patient original = patientRepository.findById(patient.getId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        original.setAddress(patient.getAddress());
        original.setDob(new java.sql.Date(patient.getDob().getTime()));
        original.setSex(patient.getSex());
        original.setPhone(patient.getPhone());

        return patientRepository.save(original);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + id));
        patientRepository.delete(patient);
    }

    @Override
    public Patient findAPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + id));
    }

    @Override
    public Optional<Patient> findByFamilyAndGiven(String family, String given) {
        return patientRepository.findByFamilyAndGiven(family, given);
    }
}
