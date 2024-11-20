package com.openclassrooms.mediscreen.controller;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ThymeleafControllerTest {

    @Mock
    private PatientService patientService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ThymeleafController thymeleafController;

    private Patient testPatient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testPatient = new Patient(
                "John", "Doe", "M", "123 Main St", "123-456-7890",
                Date.valueOf("1980-01-01")
        );
    }

    @Test
    void testListPatients() {
        List<Patient> patients = Arrays.asList(testPatient);
        when(patientService.findAll()).thenReturn(patients);

        String viewName = thymeleafController.listPatients(model);

        verify(patientService, times(1)).findAll();
        verify(model, times(1)).addAttribute("patients", patients);
        assertEquals("all", viewName);
    }

    @Test
    void testEditPatientForm() {
        Long patientId = 1L;
        when(patientService.findAPatient(patientId)).thenReturn(testPatient);

        String viewName = thymeleafController.editPatientForm(patientId, model);

        verify(patientService, times(1)).findAPatient(patientId);
        verify(model, times(1)).addAttribute("patient", testPatient);
        assertEquals("edit", viewName);
    }

    @Test
    void testShowAddPatientForm() {
        String viewName = thymeleafController.showAddPatientForm(model);

        verify(model, times(1)).addAttribute("patient", new Patient());
        assertEquals("add", viewName);
    }

    @Test
    void testAddPatientWithValidData() {
        when(bindingResult.hasErrors()).thenReturn(false); // Simulate no validation errors

        String viewName = thymeleafController.addPatient(testPatient, bindingResult, model);

        verify(patientService, times(1)).addPatient(testPatient); // Verify the service method is called
        assertEquals("redirect:/patients", viewName); // Check redirection to patients list
    }

    @Test
    void testAddPatientWithValidationErrors() {
        when(bindingResult.hasErrors()).thenReturn(true); // Simulate validation errors

        String viewName = thymeleafController.addPatient(testPatient, bindingResult, model);

        verify(patientService, times(0)).addPatient(testPatient); // Service should not be called
        verify(model, times(1)).addAttribute("patient", testPatient); // Verify patient is added to model
        assertEquals("add", viewName); // Return to the add form with errors
    }

    @Test
    void testUpdatePatientWithValidData() {
        when(bindingResult.hasErrors()).thenReturn(false); // Simulate no validation errors

        String viewName = thymeleafController.updatePatient(testPatient, bindingResult, model);

        verify(patientService, times(1)).updatePatient(testPatient); // Verify the service method is called
        assertEquals("redirect:/patients", viewName); // Check redirection to patients list
    }

    @Test
    void testUpdatePatientWithValidationErrors() {
        when(bindingResult.hasErrors()).thenReturn(true); // Simulate validation errors

        String viewName = thymeleafController.updatePatient(testPatient, bindingResult, model);

        verify(patientService, times(0)).updatePatient(testPatient); // Service should not be called
        verify(model, times(1)).addAttribute("patient", testPatient); // Verify patient is added to model
        assertEquals("edit", viewName); // Return to the edit form with errors
    }
}
