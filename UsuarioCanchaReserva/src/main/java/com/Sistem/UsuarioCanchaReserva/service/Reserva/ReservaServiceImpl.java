package com.Sistem.UsuarioCanchaReserva.service.Reserva;

import com.Sistem.UsuarioCanchaReserva.enums.EstadoReserva;
import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import com.Sistem.UsuarioCanchaReserva.exception.customs.RecursoNoEncontradoException;
import com.Sistem.UsuarioCanchaReserva.exception.customs.ReglaNegocioException;
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
    private Usuario obtenerUsuario(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Usuario no encontrado con id: " + id)
                );
    }

    private Cancha obtenerCancha(Long id){
        return canchaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Cancha no encontrada con id: " + id)
                );
    }

    private Reserva obtenerReserva(Long id){
        return reservaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Reserva no encontrada con id: " + id)
                );
    }
    @Override
    public ReservaResponse crearReserva(ReservaRequest reserva){
        Usuario usuario = obtenerUsuario(reserva.getUsuarioId());

        if(!usuario.isActivo()){
            throw new ReglaNegocioException("El usuario esta inactivo");
        }

        Cancha cancha = obtenerCancha(reserva.getCanchaId());

        if (!cancha.isDisponible()) {
            throw new ReglaNegocioException("Cancha no disponible");
        }

        if (!reserva.getHoraInicio().isBefore(reserva.getHoraFinal())) {
            throw new ReglaNegocioException("horaInicio debe ser menor que horaFin");
        }


        boolean conflicto = reservaRepository.existeConflicto(
                cancha.getId(),
                reserva.getHoraInicio(),
                reserva.getHoraFinal()
        );

        if (conflicto) {
            throw new ReglaNegocioException("La cancha ya está reservada en ese horario");
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
        Reserva reserva = obtenerReserva(id);

        return ReservaMapper.toResponse(reserva);
    }

    @Override
    public ReservaResponse cancelarReserva(Long id) {
        Reserva reserva = obtenerReserva(id);

        if (reserva.getEstado() == EstadoReserva.CANCELADA) {
            throw new ReglaNegocioException("La reserva ya está cancelada");
        }

        reserva.setEstado(EstadoReserva.CANCELADA);

        Reserva guard = reservaRepository.save(reserva);

        return ReservaMapper.toResponse(guard);
    }
}


