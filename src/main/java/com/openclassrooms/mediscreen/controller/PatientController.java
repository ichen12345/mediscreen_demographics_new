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

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findAPatient(@PathVariable Long id) {
        try {
            Patient patient = patientService.findAPatient(id);
            return ResponseEntity.ok(patient); // Return 200 OK with the patient data
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found
        }
    }


    @Validated
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping("/add")
    public Patient create(@Valid @RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

    @PutMapping
    public Patient update(@RequestBody Patient patient) {
        System.out.println("Received Patient: " + patient);
        Patient response = patientService.updatePatient(patient);
        return response;
    }

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

    @GetMapping("/search")
    public ResponseEntity<Patient> getPatientByNames(@RequestParam String family, @RequestParam String given) {
        return patientService.findByFamilyAndGiven(family, given)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Exception handler for validation errors
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return ResponseEntity.badRequest().body(errors);
//    }

}
