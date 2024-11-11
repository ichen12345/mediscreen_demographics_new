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

    @GetMapping("")
    public String listPatients(Model model) {
        List<Patient> patients = patientService.findAll();
        System.out.println("Patients: " + patients);
        model.addAttribute("patients", patients);
        return "all"; // The name of your Thymeleaf template
    }

    @GetMapping("/edit/{id}")
    public String editPatientForm(@PathVariable Long id, Model model) {
        Patient patient = patientService.findAPatient(id);
        model.addAttribute("patient", patient);
        return "edit";
    }
    @PostMapping("/update")
    public String updatePatient(@ModelAttribute Patient patient) {
        patientService.updatePatient(patient);
        return "redirect:/patients"; // Redirect to the patients list page
    }

    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add"; // Add patient form page
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patientService.addPatient(patient);
        return "redirect:/patients"; // Redirect to the patients list page
    }
}
