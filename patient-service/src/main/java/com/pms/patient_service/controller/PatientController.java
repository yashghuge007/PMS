package com.pms.patient_service.controller;

import com.pms.patient_service.dto.PatientRequestDto;
import com.pms.patient_service.dto.PatientResponseDto;
import com.pms.patient_service.dto.validators.CreatePatientValidationGroup;
import com.pms.patient_service.service.PatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1/patients")
@Tag(name = "Patient", description = "API to manage Patients")
public class PatientController {

  @Autowired
  private PatientService patientService;

  @GetMapping
  public ResponseEntity<List<PatientResponseDto>> getPatients() {
    List<PatientResponseDto> patientResponseDTOS = patientService.getPatients();
    return ResponseEntity.ok().body(patientResponseDTOS);
  }

  @PostMapping
  public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class,
      CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
    PatientResponseDto res = patientService.createPatient(patientRequestDto);
    return ResponseEntity.ok().body(res);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id,
      @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto) {
    PatientResponseDto updatedPatient = patientService.updatePatient(id, patientRequestDto);
    return ResponseEntity.ok().body(updatedPatient);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
    patientService.deletePatient(id);
    return ResponseEntity.noContent().build();
  }
}
