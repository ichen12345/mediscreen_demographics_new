package com.openclassrooms.mediscreen.service;

import com.openclassrooms.mediscreen.entity.Patient;
import com.openclassrooms.mediscreen.repository.PatientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private EntityManager entityManager;

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setGiven("John");
        patient.setFamily("Doe");
        patient.setAddress("123 Main St");
        patient.setDob(new Date(1990-10-15));
        patient.setSex("M");
        patient.setPhone("100-222-3333");
    }

    @Test
    public void testAddPatient() {
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient result = patientService.addPatient(patient);

        assertNotNull(result);
        assertEquals(patient, result);
        verify(patientRepository, times(1)).save(patient);
        verify(entityManager, times(1)).flush();
    }

    @Test
    public void testFindAll() {
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient));

        List<Patient> result = patientService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testUpdatePatient() {
        // Mock the repository to return the original patient when found
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        // Set updated fields on the patient object
        patient.setAddress("456 New St");
        patient.setDob(Date.valueOf("1990-10-16")); // Update DOB

        // Mock the save method to return the updated patient
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Call the service method with the modified patient
        Patient result = patientService.updatePatient(patient);

        // Verify the results
        assertNotNull(result);
        assertEquals("456 New St", result.getAddress());
        assertEquals(Date.valueOf("1990-10-16"), result.getDob());
        verify(patientRepository, times(1)).findById(patient.getId());
        verify(patientRepository, times(1)).save(patient);
    }



    @Test
    public void testUpdatePatient_NotFound() {
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            patientService.updatePatient(patient);
        });

        assertEquals("Patient not found", exception.getMessage());
        verify(patientRepository, times(1)).findById(patient.getId());
    }

    @Test
    void testDeletePatient_Success() {
        Long patientId = 1L;
        Patient patient = new Patient();
        patient.setId(patientId);

        // Mock repository behavior
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patient));
        doNothing().when(patientRepository).delete(patient);

        // Call the method
        patientService.deletePatient(patientId);

        // Verify repository interactions
        verify(patientRepository).findById(patientId);
        verify(patientRepository).delete(patient);
    }

    @Test
    void testDeletePatient_NotFound() {
        Long patientId = 1L;

        // Mock repository to return empty Optional, triggering the exception
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // Expect EntityNotFoundException when the service method is called
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            patientService.deletePatient(patientId);
        });

        // Assert the exception message
        assertEquals("Patient not found with ID: " + patientId, exception.getMessage());

        // Verify repository interaction
        verify(patientRepository).findById(patientId);
    }

    @Test
    public void testFindAPatient() {
        when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));

        Patient result = patientService.findAPatient(patient.getId());

        assertNotNull(result);
        assertEquals(patient.getId(), result.getId());
        verify(patientRepository, times(1)).findById(patient.getId());
    }

    @Test
    void testFindAPatient_NotFound() {
        // Mock the behavior of the repository to return an empty optional
        when(patientRepository.findById(2L)).thenReturn(Optional.empty());

        // Call the service method and expect an exception
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            patientService.findAPatient(2L);
        });

        // Verify the exception message
        assertEquals("Patient not found with ID: 2", exception.getMessage());
        verify(patientRepository, times(1)).findById(2L);
    }


    @Test
    public void testFindByFamilyAndGiven_PatientFound() {
        // Mock a patient
        Patient patient = new Patient();
        patient.setFamily("Doe");
        patient.setGiven("John");

        // Mock the repository response
        when(patientRepository.findByFamilyAndGiven("Doe", "John")).thenReturn(Optional.of(patient));

        // Call the service method
        Optional<Patient> result = patientService.findByFamilyAndGiven("Doe", "John");

        // Verify the result
        assertTrue(result.isPresent());
        assertEquals("Doe", result.get().getFamily());
        assertEquals("John", result.get().getGiven());
        verify(patientRepository, times(1)).findByFamilyAndGiven("Doe", "John");
    }

    @Test
    public void testFindByFamilyAndGiven_PatientNotFound() {
        // Mock the repository to return an empty Optional
        when(patientRepository.findByFamilyAndGiven("Doe", "Jane")).thenReturn(Optional.empty());

        // Call the service method
        Optional<Patient> result = patientService.findByFamilyAndGiven("Doe", "Jane");

        // Verify the result
        assertFalse(result.isPresent());
        verify(patientRepository, times(1)).findByFamilyAndGiven("Doe", "Jane");
    }

}
