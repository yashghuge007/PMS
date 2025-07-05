package com.pms.analytics_service.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Service
public class CustomKafkaConsumer {
  private static final Logger logger = LoggerFactory.getLogger(CustomKafkaConsumer.class);

  @KafkaListener(topics = "patient", groupId = "analytics-service")
  public void consumeEvent(byte[] event) {
    try {
      PatientEvent patientEvent = PatientEvent.parseFrom(event);
      //any business logic goes here
      logger.info("Received a event with ddetails : {}", patientEvent.toString());
    }
    catch (InvalidProtocolBufferException e) {
      logger.error("Error deserializing an event : {}", e.getMessage());
    }
  }
}
