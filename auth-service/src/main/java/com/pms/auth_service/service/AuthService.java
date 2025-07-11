package com.pms.auth_service.service;

import com.pms.auth_service.dto.LoginRequestDto;
import com.pms.auth_service.repository.UserRepository;
import com.pms.auth_service.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private final JwtUtil jwtUtil;

  public Optional<String> authenticate(LoginRequestDto loginRequestDto) {

    var abc = userRepository.findByEmail(loginRequestDto.getEmail());
    var xyz = abc.filter(u -> passwordEncoder.matches(loginRequestDto.getPassword(), u.getPassword()));
    Optional<String> token =
        xyz.map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));

    return token;
  }
}
