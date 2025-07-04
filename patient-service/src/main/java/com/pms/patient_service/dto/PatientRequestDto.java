package com.pms.patient_service.dto;

import com.pms.patient_service.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDto {
  @NotBlank(message = "Name is required")
  @Size(max = 100, message = "Name can not exceed 100 characters")
  private String name;

  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Address is required")
  private String address;

  @NotBlank(message = "Date of Birth is required")
  private String dateOfBirth;

  @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registration Date is required")
  private String registrationDate;
}
