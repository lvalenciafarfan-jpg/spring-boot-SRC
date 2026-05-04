package com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos;

import lombok.Data;

@Data
public class CanchaResponse {
    private String nombre;
    private String tipo;
    private Long precioPorHora;
    private boolean disponibilidad;
}
