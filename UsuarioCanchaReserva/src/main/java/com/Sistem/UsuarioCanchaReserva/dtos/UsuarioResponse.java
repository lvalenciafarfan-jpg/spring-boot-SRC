package com.Sistem.UsuarioCanchaReserva.dtos;

import lombok.Data;

@Data
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String correo;
    private Long numero;
    private boolean activo;
}
