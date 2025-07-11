package com.pms.auth_service.controller;

import com.pms.auth_service.dto.LoginRequestDto;
import com.pms.auth_service.dto.LoginResponseDto;
import com.pms.auth_service.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuthController {
  @Autowired
  private final AuthService authService;

  @PostMapping("api/v1/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
    Optional<String> tokenOptional = authService.authenticate(loginRequestDto);
    if (tokenOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    var token = tokenOptional.get();
    return ResponseEntity.ok(new LoginResponseDto(token));
  }
}
