package com.pms.patient_service.kafka;

import com.pms.patient_service.model.Patient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@AllArgsConstructor
@Service
public class CustomKafkaProducer {
  @Autowired
  private final KafkaTemplate<String, byte[]> kafkaTemplate;
  private final Logger logger = LoggerFactory.getLogger(CustomKafkaProducer.class);

  public void sendEvent(Patient patient) {
    PatientEvent patientEvent = PatientEvent.newBuilder()
                                    .setPatientId(patient.getId().toString())
                                    .setName(patient.getName())
                                    .setEmail(patient.getEmail())
                                    .setEventType("PATIENT_CREATED")
                                    .build();

    try {
      kafkaTemplate.send("patient", patientEvent.toByteArray());
    }
    catch (Exception ex) {
      logger.error("Error sending PatientCreated Event {}", ex.getMessage());
    }
  }
}
