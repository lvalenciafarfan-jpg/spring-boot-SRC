package com.Sistem.UsuarioCanchaReserva.serviceImpl;

import com.Sistem.UsuarioCanchaReserva.EstadoReserva;
import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import com.Sistem.UsuarioCanchaReserva.repository.ReservaRepository;
import com.Sistem.UsuarioCanchaReserva.repository.UsuarioRepository;
import com.Sistem.UsuarioCanchaReserva.repository.CanchaRepository;
import com.Sistem.UsuarioCanchaReserva.service.ReservaService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CanchaRepository canchaRepository;

    public ReservaServiceImpl(
            ReservaRepository reservaRepository,
            UsuarioRepository usuarioRepository,
            CanchaRepository canchaRepository
    ){
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.canchaRepository = canchaRepository;
    }

    @Override
    public Reserva crearReserva(Reserva reserva){

        Usuario usuario = usuarioRepository.findById(reserva.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        if (!usuario.isActivo()) {
            throw new RuntimeException("Usuario inactivo");
        }


        Cancha cancha = canchaRepository.findById(reserva.getCancha().getId())
                .orElseThrow(() -> new RuntimeException("Cancha no existe"));


        if (!cancha.isDisponible()) {
            throw new RuntimeException("Cancha no disponible");
        }

        if (!reserva.getHoraInicio().isBefore(reserva.getHoraFinal())) {
            throw new RuntimeException("horaInicio debe ser menor que horaFin");
        }


        boolean conflicto = reservaRepository.existeConflicto(
                cancha.getId(),
                reserva.getHoraInicio(),
                reserva.getHoraFinal()
        );

        if (conflicto) {
            throw new RuntimeException("La cancha ya está reservada en ese horario");
        }

        reserva.setEstado(EstadoReserva.ACTIVA);

        return reservaRepository.save(reserva);
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Optional<Reserva> listarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("La reserva ya está cancelada");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);

        return reservaRepository.save(reserva);
    }
}


