package com.mediscreen.patients;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Test
	public void testAddPatient() throws Exception {
		Patient patient = new Patient();
		patient.setFirstName("firstname");
		patient.setLastName("lastname");
		patient.setAddress("address");
		patient.setGender("M");
		patient.setPhone("1111");
		patient.setPatientId((long) 1);	
		
		when(patientService.createPatient(patient)).thenReturn(patient);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/patient/add")
				.contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(patient)))
		.andExpect(status().isOk());
		
		
	}
	
	
	

	@Test
	public void testUpdatePatient() throws Exception {
		Patient patient = new Patient();
		patient.setFirstName("firstname");
		patient.setLastName("lastname");
		patient.setAddress("address");
		patient.setGender("M");
		patient.setPhone("1111");
		patient.setPatientId((long) 1);	
		
		mockMvc.perform(MockMvcRequestBuilders.put("/patient/update")
				.contentType(MediaType.APPLICATION_JSON)
		        .content(asJsonString(patient)))
		.andExpect(status().isOk());
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
