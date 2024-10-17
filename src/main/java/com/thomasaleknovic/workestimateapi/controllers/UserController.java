package com.thomasaleknovic.workestimateapi.controllers;


import com.thomasaleknovic.workestimateapi.dtos.AuthenticationDTO;
import com.thomasaleknovic.workestimateapi.services.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final AuthorizationService authorizationService;

    @GetMapping
    public ResponseEntity<String> getUser (@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(user.getUsername());
    }

    @PutMapping
    @RequestMapping("/edit")
    public ResponseEntity<String> updateUserName(@AuthenticationPrincipal UserDetails user,
                                                 @RequestBody AuthenticationDTO data) {
        authorizationService.updateUser(user, data.username());
        return ResponseEntity.ok().body("Nome de usuário atualizado com sucesso!");

    }
}
