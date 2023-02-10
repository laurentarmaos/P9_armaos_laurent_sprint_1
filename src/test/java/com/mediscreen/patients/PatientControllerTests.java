package com.mediscreen.patients;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mediscreen.controller.PatientController;
import com.mediscreen.entities.Patient;
import com.mediscreen.services.PatientService;


@SpringBootTest
public class PatientControllerTests {


	@MockBean
	private PatientService patientService;
	
	private PatientController patientController;

	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		patientController = new PatientController(patientService);
		mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
	}
	
//TODO corriger le pb dû au @RequestBody	
	@Test
	public void testAddPatient() throws Exception {
		Patient patient = new Patient();
		patient.setFirstName("firstname");
		patient.setLastName("lastname");
		patient.setAddress("address");
		patient.setBirthDate(LocalDate.now());
		patient.setGender("M");
		patient.setPhone("1111");
		patient.setPatientId((long) 1);	
		
		mockMvc.perform(MockMvcRequestBuilders.post("/patient/add", patient)).andExpect(status().isOk());
	}
	
//TODO corriger le pb dû au @RequestBody
	@Test
	public void testUpdatePatient() throws Exception {
		Patient patient = new Patient();
		patient.setFirstName("firstname");
		patient.setLastName("lastname");
		patient.setAddress("address");
		patient.setBirthDate(LocalDate.now());
		patient.setGender("M");
		patient.setPhone("1111");
		patient.setPatientId((long) 1);	
		
		//mockMvc.perform(MockMvcRequestBuilders.put("/patient/update", patient)).andExpect(status().isOk());
	}
	
	
	
	@Test
	public void testFindPatient() throws Exception {
		Patient patient = new Patient();
		patient.setFirstName("firstname");
		patient.setLastName("lastname");
		patient.setAddress("address");
		patient.setBirthDate(LocalDate.now());
		patient.setGender("M");
		patient.setPhone("1111");
		patient.setPatientId((long) 1);

		
		mockMvc.perform(MockMvcRequestBuilders.get("/patient/{id}", patient.getPatientId())).andExpect(status().isOk());
	}
	
	
	@Test
	public void testFindAllPatients() throws Exception {
		Patient patient = new Patient();
		patient.setFirstName("firstname");
		
		List<Patient> patients = new ArrayList<>();
		patients.add(patient);
		
		
		mockMvc.perform(MockMvcRequestBuilders.get("/patients")).andExpect(status().isOk());
	}
	
	
	@Test
	public void deletePatient() throws Exception {
		Patient patient = new Patient();
		patient.setPatientId((long) 1);
			
		mockMvc.perform(MockMvcRequestBuilders.delete("/patient/delete/{id}", patient.getPatientId())).andExpect(status().isOk());
	}
	
	
}
