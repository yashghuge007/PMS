package com.pms.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponseDto {
  private String token;
}
