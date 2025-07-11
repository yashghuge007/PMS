package com.pms.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
  @NotBlank(message = "Email is required")
  @Email(message = "Email should be a valid email address")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Email should be a valid email address")
  private String password;
}
