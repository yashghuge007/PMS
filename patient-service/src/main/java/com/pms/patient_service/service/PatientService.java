package com.pms.patient_service.service;

import com.pms.patient_service.dto.PatientRequestDto;
import com.pms.patient_service.dto.PatientResponseDto;
import com.pms.patient_service.exception.EmailAlreadyExistsException;
import com.pms.patient_service.exception.PatientNotFoundException;
import com.pms.patient_service.mapper.PatientMapper;
import com.pms.patient_service.model.Patient;
import com.pms.patient_service.repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PatientService {

  @Autowired
  private PatientRepository patientRepository;

  public List<PatientResponseDto> getPatients() {
    List<Patient> patients = patientRepository.findAll();
    return patients.stream().map(PatientMapper::toDTO).toList();
  }

  public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
    if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
      throw new EmailAlreadyExistsException("Patient with this email already exists: " + patientRequestDto.getEmail());
    }

    Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDto));
    return PatientMapper.toDTO(patient);
  }

  public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {
    Patient patient = patientRepository.findById(id).orElseThrow(
        () -> new PatientNotFoundException("Patient not found with id: " + id));

    if (patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(), id)) {
      throw new EmailAlreadyExistsException("Patient with this email already exists: " + patientRequestDto.getEmail());
    }

    patient.setName(patientRequestDto.getName());
    patient.setAddress(patientRequestDto.getAddress());
    patient.setEmail(patientRequestDto.getEmail());
    patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));

    Patient newPatient = patientRepository.save(patient);
    return PatientMapper.toDTO(newPatient);
  }

  public void deletePatient(UUID id) {
    patientRepository.deleteById(id);
  }
}
