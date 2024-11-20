package com.openclassrooms.mediscreen.controller;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add"; // Add patient form page
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // If validation fails, return the "add" form again with error messages
            model.addAttribute("patient", patient);
            return "add"; // Thymeleaf form will display the errors
        }
        patientService.addPatient(patient);
        return "redirect:/patients"; // Redirect to the patients list page if valid
    }

    @PutMapping("/update")
    public String updatePatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // If validation fails, return the "edit" form again with error messages
            model.addAttribute("patient", patient);
            return "edit"; // Thymeleaf form will display the errors
        }
        patientService.updatePatient(patient);
        return "redirect:/patients"; // Redirect to the patients list page if valid
    }

}
