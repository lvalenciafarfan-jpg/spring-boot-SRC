package com.Sistem.UsuarioCanchaReserva.service.Reserva;
import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaService {

    Reserva crearReserva(Reserva reserva);

    List<Reserva> listarReservas();

    Optional<Reserva> listarPorId(Long id);

    Reserva cancelarReserva(Long id);
}
