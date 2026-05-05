package com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CanchaRequest {

    @NotBlank(message = "El nombre de la cancha no debe estar vacia")
    private String nombre;
    @NotNull(message = "El tipo de cancha no debe estar vacio")
    private String tipo;
    @NotNull(message = "El precioPorHora de la cancha no puede estar vacio")
    @Positive(message = "El precioPorHora debe ser un entero positivo.")
    private Long precioPorHora;
}
