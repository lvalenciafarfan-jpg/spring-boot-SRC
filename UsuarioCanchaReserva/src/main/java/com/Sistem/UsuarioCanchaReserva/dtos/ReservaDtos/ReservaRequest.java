package com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaRequest {
    @NotNull(message = "La fecha es obligatoria")
    @Future(message = "La fecha debe ser futura")
    private LocalDate fecha;
    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;
    @NotNull(message = "La horaFinal es obligatoria")
    private LocalTime horaFinal;
    @NotNull(message = "El ID del usuario es obligatoria")
    @Positive(message = "EL ID del usuario debe ser positivo")
    private Long usuarioId;
    @NotNull(message = "EL ID de la cancha es obligatoria")
    @Positive(message = "EL ID de la cancha debe ser positivo")
    private Long canchaId;
}