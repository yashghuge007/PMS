package com.pms.patient_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientResponseDto {
 private String id;
 private String name;
 private String email;
 private String address;
 private String dateOfBirth;
}
