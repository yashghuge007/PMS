package com.pms.patient_service.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class EmailAlreadyExistsException extends RuntimeException {
  public EmailAlreadyExistsException(String message) {
    super(message);
  }
}
