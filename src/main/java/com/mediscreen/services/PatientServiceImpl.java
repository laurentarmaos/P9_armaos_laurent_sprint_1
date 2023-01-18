package com.mediscreen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mediscreen.entities.Patient;
import com.mediscreen.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	private final PatientRepository patientRepository;
	
	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public Patient createPatient(Patient dto) {
		Patient entity = new Patient();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setGender(dto.getGender());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		
		patientRepository.save(entity);
		
		return entity;
	}

	@Override
	public List<Patient> findAllPatients() {
		return patientRepository.findAll();
	}
	
	@Override
	public Patient findById(Long id) {
		return patientRepository.findById(id).get();
	}

	@Override
	public Patient updatePatient(Long id, Patient dto) {
		Patient entity = patientRepository.findById(id).get();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setGender(dto.getGender());
		entity.setPhone(dto.getPhone());
		entity.setAddress(dto.getAddress());
		
		patientRepository.save(entity);
		
		return entity;
	}

	@Override
	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
		
	}

}
