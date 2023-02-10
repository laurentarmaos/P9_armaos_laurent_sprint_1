package com.mediscreen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.entities.Patient;
import com.mediscreen.services.PatientService;


@RestController
public class PatientController {
	
	private final PatientService patientService;
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	@PostMapping("/patient/add")
	public Patient addPatient(@RequestBody Patient patient){	
		return patientService.createPatient(patient);
	}
	
	
	@GetMapping("/patients")
	public List<Patient> findAllPatients() {
		return patientService.findAllPatients();
	}
	
	
	@GetMapping("/patient/{id}")
	public Patient findPatient(@PathVariable Long id) {	
		return patientService.findById(id);
	}
	
	
	@PutMapping("/patient/update")
	public Patient updatePatient(@RequestBody Patient patient) {
		return patientService.updatePatient(patient);
	}
	
	
	@DeleteMapping("/patient/delete/{id}")
	public void deletePatient(@PathVariable("id") Long id) {
		patientService.deletePatient(id);
	}
}
