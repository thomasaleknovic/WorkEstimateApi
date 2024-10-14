package com.thomasaleknovic.workestimateapi.controllers;

import com.azure.core.annotation.Get;
import com.thomasaleknovic.workestimateapi.configs.security.TokenService;
import com.thomasaleknovic.workestimateapi.dtos.AuthenticationDTO;
import com.thomasaleknovic.workestimateapi.dtos.LoginResponseDTO;
import com.thomasaleknovic.workestimateapi.dtos.RegisterDTO;
import com.thomasaleknovic.workestimateapi.exceptions.User.UserNotFoundException;
import com.thomasaleknovic.workestimateapi.models.User;
import com.thomasaleknovic.workestimateapi.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        User user = userRepository.findByUsername(authenticationDTO.username()).orElseThrow(UserNotFoundException::new);

       if(passwordEncoder.matches(authenticationDTO.password(), user.getPassword())) {
           String token = tokenService.generateToken(user);
           return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), user.getUserId() ,token));
       }
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {

        if(userRepository.findByUsername(registerDTO.username()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            String encryptedPassword = new BCryptPasswordEncoder()
                    .encode(registerDTO.password());

            User user = new User(
                    registerDTO.username(),
                    encryptedPassword,
                    registerDTO.email(),
                    registerDTO.role()
            );

            userRepository.save(user);

            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), user.getUserId() ,token));

        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }

    }


}
