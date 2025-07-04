package com.pms.patient_service.mapper;

import com.pms.patient_service.dto.PatientRequestDto;
import com.pms.patient_service.dto.PatientResponseDto;
import com.pms.patient_service.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
  public static PatientResponseDto toDTO(Patient patient){
    PatientResponseDto dto = new PatientResponseDto();
    dto.setId(patient.getId().toString());
    dto.setName(patient.getName());
    dto.setAddress(patient.getAddress());
    dto.setEmail(patient.getEmail());
    dto.setDateOfBirth(patient.getDateOfBirth().toString());
    return dto;
  }

  public static Patient toModel(PatientRequestDto patientRequestDto){
    Patient patient = new Patient();
    patient.setName(patientRequestDto.getName());
    patient.setAddress(patient.getAddress());
    patient.setEmail(patientRequestDto.getEmail());
    patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
    patient.setRegistrationDate(LocalDate.parse(patientRequestDto.getRegistrationDate()));
    return  patient;
  }
}
