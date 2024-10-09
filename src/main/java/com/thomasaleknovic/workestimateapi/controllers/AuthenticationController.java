package com.thomasaleknovic.workestimateapi.controllers;

import com.thomasaleknovic.workestimateapi.configs.security.TokenService;
import com.thomasaleknovic.workestimateapi.dtos.AuthenticationDTO;
import com.thomasaleknovic.workestimateapi.dtos.LoginResponseDTO;
import com.thomasaleknovic.workestimateapi.dtos.RegisterDTO;
import com.thomasaleknovic.workestimateapi.exceptions.User.UserNotFoundException;
import com.thomasaleknovic.workestimateapi.models.User;
import com.thomasaleknovic.workestimateapi.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.username(), authenticationDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {

        if(userRepository.findByUsername(registerDTO.username()) != null) {
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

        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
