package com.mediscreen.services;

import java.util.List;

import com.mediscreen.entities.Patient;

public interface PatientService {

	Patient createPatient(Patient patient);
	
	List<Patient> findAllPatients();
	
	Patient findById(Long id);
	
	Patient updatePatient(Long id, Patient patient);
	
	void deletePatient(Long id);
}
