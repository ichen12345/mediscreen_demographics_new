package com.openclassrooms.mediscreen.controller;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Handle requests for patients
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

    /**
     * find all patient
     *
     * @return list of patients
     */
    @GetMapping
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    /**
     * find a patient by id
     *
     * @param id patient id
     * @return patient
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findAPatient(@PathVariable Long id) {
        try {
            Patient patient = patientService.findAPatient(id);
            return ResponseEntity.ok(patient); // Return 200 OK with the patient data
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found
        }
    }

    /**
     * add a patient
     *
     * @return the added patient
     */
    @Validated
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/add")
    public Patient create(@Valid @RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    /**
     * update an existing patient
     *
     * @return the updated patient
     */
    @PutMapping
    public Patient update(@RequestBody Patient patient) {
        System.out.println("Received Patient: " + patient);
        Patient response = patientService.updatePatient(patient);
        return response;
    }

    /**
     * delete a patient by id
     *
     * @param id patient id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build(); // 204 No Content if delete successful
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found if ID does not exist
        }
    }

    /**
     * find a patient by their family and given names
     *
     * @param family
     * @param given
     * @return patient
     */
    @GetMapping("/search")
    public ResponseEntity<Patient> getPatientByNames(@RequestParam String family, @RequestParam String given) {
        return patientService.findByFamilyAndGiven(family, given)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
