package com.Sistem.UsuarioCanchaReserva.service.Reserva;
import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface ReservaService {

    ReservaResponse crearReserva(ReservaRequest reserva);

    List<ReservaResponse> listarReservas(Usuario usuario);

    ReservaResponse listarPorId(Long id, Usuario usuario);

    ReservaResponse cancelarReserva(Long id);
}
