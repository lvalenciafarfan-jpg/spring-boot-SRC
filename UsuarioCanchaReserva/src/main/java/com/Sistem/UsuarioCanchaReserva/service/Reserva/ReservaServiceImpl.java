package com.Sistem.UsuarioCanchaReserva.service.Reserva;

import com.Sistem.UsuarioCanchaReserva.EstadoReserva;
import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import com.Sistem.UsuarioCanchaReserva.mappers.ReservaMapper;
import com.Sistem.UsuarioCanchaReserva.repository.ReservaRepository;
import com.Sistem.UsuarioCanchaReserva.repository.UsuarioRepository;
import com.Sistem.UsuarioCanchaReserva.repository.CanchaRepository;

import org.springframework.stereotype.Service;

import java.util.List;


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
    public ReservaResponse crearReserva(ReservaRequest reserva){

        Usuario usuario = usuarioRepository.findById(reserva.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        if(!usuario.isActivo()){
            throw new RuntimeException("El usuario esta inactivo");
        }

        Cancha cancha = canchaRepository.findById(reserva.getCanchaId())
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

        Reserva guardado = ReservaMapper.toEntity(reserva);

        guardado.setUsuario(usuario);
        guardado.setCancha(cancha);

        Reserva guardada = reservaRepository.save(guardado);

        return ReservaMapper.toResponse(guardada);

    }

    @Override
    public List<ReservaResponse> listarReservas() {

        List<Reserva> reservas = reservaRepository.findAll();

        return reservas.stream().map(ReservaMapper::toResponse).toList();
    }

    @Override
    public ReservaResponse listarPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        return ReservaMapper.toResponse(reserva);
    }

    @Override
    public ReservaResponse cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("La reserva ya está cancelada");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);

        Reserva guard = reservaRepository.save(reserva);

        return ReservaMapper.toResponse(guard);
    }
}


