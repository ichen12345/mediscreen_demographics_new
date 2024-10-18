//package com.openclassrooms.mediscreen.controller;
//
//import com.openclassrooms.mediscreen.entity.Patient;
//import com.openclassrooms.mediscreen.service.PatientService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class PatientControllerTest {
//
//    @InjectMocks
//    private PatientController patientController;
//
//    @Mock
//    private PatientService patientService;
//
//
//    @Test
//    public void testFindAllPatients() {
//        List<Patient> patients = new ArrayList<>();
//        patients.add(new Patient());
//
//        when(patientService.findAll()).thenReturn(patients);
//
//        List<Patient> result = patientController.findAll();
//
//        assertEquals(1, result.size());
//        verify(patientService, times(1)).findAll();
//    }
//
//    @Test
//    public void testFindAPatient() {
//        Long patientId = 1L;
//        Patient patient = new Patient();
//        patient.setId(patientId);
//
//        when(patientService.findAPatient(patientId)).thenReturn(patient);
//
//        Patient result = patientController.findAPatient(patientId);
//
//        assertEquals(patientId, result.getId());
//        verify(patientService, times(1)).findAPatient(patientId);
//    }
//
//    @Test
//    public void testCreatePatient() {
//        Patient patient = new Patient();
//        patient.setGiven("John");
//        patient.setFamily("Doe");
//
//        when(patientService.addPatient(patient)).thenReturn(patient);
//
//        Patient result = patientController.create(patient);
//
//        assertNotNull(result);
//        assertEquals("John", result.getGiven());
//        verify(patientService, times(1)).addPatient(patient);
//    }
//
//    @Test
//    public void testUpdatePatient() {
//        Patient patient = new Patient();
//        patient.setId(1L);
//
//        when(patientService.updatePatient(patient)).thenReturn(patient);
//
//        Patient result = patientController.update(patient);
//
//        assertNotNull(result);
//        assertEquals(Long.valueOf(1L), result.getId());
//        verify(patientService, times(1)).updatePatient(patient);
//    }
//
//    @Test
//    public void testDeletePatient() {
//        Long patientId = 1L;
//
//        patientController.delete(patientId);
//
//        verify(patientService, times(1)).deletePatient(patientId);
//    }
//
////    @Test
////    public void testHandleValidationExceptions() {
////        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
////        Map<String, String> errors = new HashMap<>();
////        errors.put("given", "Given name is mandatory");
////
////        FieldError fieldError = mock(FieldError.class);
////        when(fieldError.getField()).thenReturn("given");
////        when(fieldError.getDefaultMessage()).thenReturn("Given name is mandatory");
////
////        when(exception.getBindingResult().getAllErrors()).thenReturn(List.of(fieldError));
////
////        ResponseEntity<Map<String, String>> response = patientController.handleValidationExceptions(exception);
////
////        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
////        assertEquals(errors, response.getBody());
////    }
//}
