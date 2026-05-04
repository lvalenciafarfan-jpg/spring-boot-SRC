package com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos;

import com.Sistem.UsuarioCanchaReserva.enums.EstadoReserva;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaResponse {
    private Long id;
    private EstadoReserva estado;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFinal;

    private Long canchaId;
    private String canchaNombre;

    private Long usuarioId;
    private String usuarioNombre;
}