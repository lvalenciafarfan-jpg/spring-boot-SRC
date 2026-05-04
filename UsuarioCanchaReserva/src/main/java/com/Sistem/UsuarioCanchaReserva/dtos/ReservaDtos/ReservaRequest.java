package com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaRequest {
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private Long usuarioId;
    private Long canchaId;
}