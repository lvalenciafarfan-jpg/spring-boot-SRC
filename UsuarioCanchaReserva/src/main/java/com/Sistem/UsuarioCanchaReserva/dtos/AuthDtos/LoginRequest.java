package com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de email invalido")
    private String email;
    @NotNull(message = "La password es obligatoria")
    private String password;
}
