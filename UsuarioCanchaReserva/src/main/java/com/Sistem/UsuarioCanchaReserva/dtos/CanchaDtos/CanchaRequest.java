package com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos;

import lombok.Data;

@Data
public class CanchaRequest {
    private String nombre;
    private String tipo;
    private Long precioPorHora;
}
