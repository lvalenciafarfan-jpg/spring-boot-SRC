package com.Sistem.UsuarioCanchaReserva.entities;

import com.Sistem.UsuarioCanchaReserva.EstadoReserva;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Reserva{

    public Reserva(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate fecha;

    @NotNull
    private LocalTime horaInicio;

    @NotNull
    private LocalTime horaFinal;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado = EstadoReserva.ACTIVA;

    @ManyToOne
    @JoinColumn(name = "cancha_id")
    private Cancha cancha;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}