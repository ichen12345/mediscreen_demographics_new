package com.openclassrooms.mediscreen.controller;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientService patientService;

    @Test
    public void testFindAllPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());

        when(patientService.findAll()).thenReturn(patients);

        List<Patient> result = patientController.findAll();

        assertEquals(1, result.size());
        verify(patientService, times(1)).findAll();
    }

    @Test
    public void testFindAPatient_Success() {
        // Mock the patientService to return a valid patient
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setGiven("John");
        patient.setFamily("Doe");
        patient.setAddress("123 Main St");
        patient.setDob(java.sql.Date.valueOf("1990-10-15"));
        patient.setSex("M");
        patient.setPhone("100-222-3333");

        when(patientService.findAPatient(1L)).thenReturn(patient);

        // Call the controller method
        ResponseEntity<Patient> response = patientController.findAPatient(1L);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(patient.getId(), response.getBody().getId());
        assertEquals(patient.getGiven(), response.getBody().getGiven());
        assertEquals(patient.getFamily(), response.getBody().getFamily());
        verify(patientService, times(1)).findAPatient(1L);
    }

    @Test
    public void testFindAPatient_NotFound() {
        // Mock the service to throw EntityNotFoundException
        when(patientService.findAPatient(2L)).thenThrow(new EntityNotFoundException("Patient not found with ID: 2"));

        // Call the controller method
        ResponseEntity<Patient> response = patientController.findAPatient(2L);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody()); // The body should be null for a 404 response
        verify(patientService, times(1)).findAPatient(2L);
    }

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient();
        patient.setGiven("John");
        patient.setFamily("Doe");

        when(patientService.addPatient(patient)).thenReturn(patient);

        Patient result = patientController.create(patient);

        assertNotNull(result);
        assertEquals("John", result.getGiven());
        verify(patientService, times(1)).addPatient(patient);
    }

    @Test
    public void testUpdatePatient() {
        Patient patient = new Patient();
        patient.setId(1L);

        when(patientService.updatePatient(patient)).thenReturn(patient);

        Patient result = patientController.update(patient);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        verify(patientService, times(1)).updatePatient(patient);
    }

    @Test
    public void testDeletePatient() {
        Long patientId = 1L;

        patientController.delete(patientId);

        verify(patientService, times(1)).deletePatient(patientId);
    }

    @Test
    public void testDeletePatient_NotFound() {
        // Mock the service to throw EntityNotFoundException
        doThrow(new EntityNotFoundException("Patient not found with ID: 2"))
                .when(patientService).deletePatient(2L);

        // Call the controller method and assert a 404 response
        ResponseEntity<Void> response = patientController.delete(2L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(patientService, times(1)).deletePatient(2L);
    }

    @Test
    public void testGetPatientByNames_Found() {
        // Arrange
        String familyName = "Doe";
        String givenName = "John";
        Patient patient = new Patient();
        patient.setFamily(familyName);
        patient.setGiven(givenName);

        when(patientService.findByFamilyAndGiven(familyName, givenName)).thenReturn(Optional.of(patient));

        // Act
        ResponseEntity<Patient> response = patientController.getPatientByNames(familyName, givenName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(givenName, response.getBody().getGiven());
        assertEquals(familyName, response.getBody().getFamily());
        verify(patientService, times(1)).findByFamilyAndGiven(familyName, givenName);
    }

    @Test
    public void testGetPatientByNames_NotFound() {
        // Arrange
        String familyName = "Unknown";
        String givenName = "Name";

        when(patientService.findByFamilyAndGiven(familyName, givenName)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Patient> response = patientController.getPatientByNames(familyName, givenName);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(patientService, times(1)).findByFamilyAndGiven(familyName, givenName);
    }

}