package com.Sistem.UsuarioCanchaReserva.entities;

import com.Sistem.UsuarioCanchaReserva.EstadoReserva;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Reserva{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDateTime fecha;

    @NotNull
    private LocalTime horaInicio;
    
    @NotNull
    private LocalTime horaFinal;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado = EstadoReserva.ACTIVA;


}