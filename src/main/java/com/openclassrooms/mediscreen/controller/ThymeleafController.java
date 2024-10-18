package com.openclassrooms.mediscreen.controller;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class ThymeleafController {
    @Autowired
    PatientService patientService;

//    @GetMapping
//    public List<Patient> findAll() {
//        return patientService.findAll();
//    }

//    @GetMapping("/{id}")
//    public Patient findAPatient(@PathVariable Long id) {
//        return patientService.findAPatient(id);
//    }
//
//    @Validated
//    @ResponseStatus(HttpStatus.CREATED) // 201
//    @PostMapping("/add")
//    public Patient create(@Valid @RequestBody Patient patient) {
//        return patientService.addPatient(patient);
//    }
//
//    @PutMapping
//    public Patient update(@RequestBody Patient patient) {
//        Patient response = patientService.updatePatient(patient);
//        return response;
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT) //204
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        patientService.deletePatient(id);
//    }

    @GetMapping("")
    public String listPatients(Model model) {
        List<Patient> patients = patientService.findAll();
        model.addAttribute("patients", patients);
        return "patients"; // The name of your Thymeleaf template
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
