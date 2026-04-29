package com.Sistem.UsuarioCanchaReserva.service;
import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import java.util.List;

public interface ReservaService {

    Reserva crearReserva(Reserva reserva);

    List<Reserva> listarReservas();

    Reserva listarPorId(Long id);

    Reserva cancelarReserva(Long id);
}
