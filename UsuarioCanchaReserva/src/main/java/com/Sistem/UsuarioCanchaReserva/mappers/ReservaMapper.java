package com.Sistem.UsuarioCanchaReserva.mappers;

import com.Sistem.UsuarioCanchaReserva.EstadoReserva;
import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;

public class ReservaMapper {

    public static Reserva toEntity(ReservaRequest datos){
        Reserva reserva = new Reserva();

        reserva.setFecha(datos.getFecha());
        reserva.setHoraInicio(datos.getHoraInicio());
        reserva.setHoraFinal(datos.getHoraFinal());

        Usuario usuario = new Usuario();
        usuario.setId(datos.getUsuarioId());
        reserva.setUsuario(usuario);

        Cancha cancha = new Cancha();
        cancha.setId(datos.getCanchaId());
        reserva.setCancha(cancha);

        reserva.setEstado(EstadoReserva.ACTIVA);

        return reserva;
    }

    public static ReservaResponse toResponse(Reserva reserva){
        ReservaResponse dto = new ReservaResponse();

        dto.setId(reserva.getId());
        dto.setEstado(reserva.getEstado());

        dto.setFecha(reserva.getFecha());
        dto.setHoraInicio(reserva.getHoraInicio());
        dto.setHoraFinal(reserva.getHoraFinal());

        dto.setCanchaId(reserva.getCancha().getId());
        dto.setCanchaNombre(reserva.getCancha().getNombre());

        dto.setUsuarioId(reserva.getUsuario().getId());
        dto.setUsuarioNombre(reserva.getUsuario().getNombre());

        return dto;
    }
}
