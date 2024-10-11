package com.openclassrooms.mediscreen.service;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        original = patientRepository.getReferenceById(patient.getId());
        original.setAddress(patient.getAddress());
        original.setDob(patient.getDob());
        original.setSex(patient.getSex());
        original.setPhone(patient.getPhone());

        return patientRepository.save(original);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new EntityNotFoundException("Patient not found");
        }
        patientRepository.deleteById(id);
    }
}
