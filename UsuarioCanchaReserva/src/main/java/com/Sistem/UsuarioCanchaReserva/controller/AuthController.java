package com.Sistem.UsuarioCanchaReserva.controller;

import com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos.LoginRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos.LoginResponse;
import com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos.RegistroRequest;
import com.Sistem.UsuarioCanchaReserva.service.Auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@Valid @RequestBody RegistroRequest request) {
        authService.registro(request);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}