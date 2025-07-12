package com.pms.auth_service.controller;

import com.pms.auth_service.dto.LoginRequestDto;
import com.pms.auth_service.dto.LoginResponseDto;
import com.pms.auth_service.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth/api/v1")
@AllArgsConstructor
public class AuthController {
  @Autowired
  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
    Optional<String> tokenOptional = authService.authenticate(loginRequestDto);
    if (tokenOptional.isEmpty()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    var token = tokenOptional.get();
    return ResponseEntity.ok(new LoginResponseDto(token));
  }

  @GetMapping("api/v1/validate")
  public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader) {
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return authService.validateToken(authHeader.substring(7)) // Remove "Bearer " prefix
               ? ResponseEntity.ok().build()
               : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }
}
