package com.openclassrooms.mediscreen.service;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Patient patient) {
        Patient original = patientRepository.getReferenceById(patient.getId());
        original.setAddress(patient.getAddress());
        original.setDob(patient.getDob());
        original.setSex(patient.getSex());
        original.setPhone(patient.getPhone());

        return original;

    }

    @Override
    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }
}
