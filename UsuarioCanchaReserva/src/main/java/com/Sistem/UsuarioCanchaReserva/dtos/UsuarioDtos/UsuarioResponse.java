package com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos;

import lombok.Data;

@Data
public class UsuarioResponse {
    private String nombre;
    private String correo;
    private Long numero;
    private boolean activo;
}
