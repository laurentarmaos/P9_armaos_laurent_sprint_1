package com.mediscreen.patients;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mediscreen.entities.Patient;
import com.mediscreen.repository.PatientRepository;
import com.mediscreen.services.PatientService;
import com.mediscreen.services.PatientServiceImpl;

@SpringBootTest
public class PatientServiceTests {
	
	@MockBean
	private PatientRepository patientRepository;
	
	private PatientService patientService;
	
	@BeforeEach
	public void setUp() {
		patientService = new PatientServiceImpl(patientRepository);
	}
	
	
	@Test
	public void testCreatePatient() {
		
		Patient patient = new Patient();
		patient.setFirstName("firstname");
		patient.setLastName("lastname");
		
		when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
		
		Patient savedPatient = patientService.createPatient(patient);
		
		assertNotNull(savedPatient);
		assertEquals(savedPatient.getFirstName(), "firstname");
		assertEquals(savedPatient.getLastName(), "lastname");
		verify(patientRepository).save(Mockito.any(Patient.class));
	}
	
	@Test
	public void testFindAllPatients() {
		Patient patient1 = new Patient();
		patient1.setFirstName("firstname");
		
		Patient patient2 = new Patient();
		patient2.setFirstName("firstname");
		
		List<Patient> patients = new ArrayList<>();
		patients.add(patient1);
		patients.add(patient2);
		
		when(patientRepository.findAll()).thenReturn(patients);
		
		List<Patient> savedPatients = patientService.findAllPatients();
		
		assertEquals(savedPatients.size(), 2);
		assertEquals(savedPatients.get(0), patient1);
	}
	
	@Test
	public void testFindPatientById() {
		Patient patient = new Patient();
		patient.setFirstName("firstname");
		patient.setPatientId((long) 1);
		
		when(patientRepository.findByPatientId(Mockito.anyLong())).thenReturn(patient);
		
		Patient foundPatient = patientService.findById((long) 1);
		
		assertNotNull(foundPatient);
		assertEquals(foundPatient.getFirstName(), "firstname");
		verify(patientRepository).findByPatientId(Mockito.anyLong());
	}
	
	@Test
	public void testUpdatePatient() {
		Patient patient = new Patient();
		patient.setFirstName("firstname");
		
		when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
		
		Patient updatedPatient = patientService.updatePatient(patient);
		
		assertNotNull(updatedPatient);
		assertEquals(updatedPatient.getFirstName(), "firstname");
		verify(patientRepository).save(Mockito.any(Patient.class));
	}
	
	@Test
	public void testDeletePatient() {
		patientService.deletePatient((long)1);
		
		verify(patientRepository).deleteById(Mockito.anyLong());
	}

}
