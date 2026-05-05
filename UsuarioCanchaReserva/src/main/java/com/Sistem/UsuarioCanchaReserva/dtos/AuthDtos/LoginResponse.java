package com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
}
