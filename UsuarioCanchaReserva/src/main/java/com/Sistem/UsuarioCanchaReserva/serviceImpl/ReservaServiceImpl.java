package com.Sistem.UsuarioCanchaReserva.serviceImpl;

import com.Sistem.UsuarioCanchaReserva.EstadoReserva;
import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import com.Sistem.UsuarioCanchaReserva.repository.ReservaRepository;
import com.Sistem.UsuarioCanchaReserva.service.ReservaService;

import java.util.List;
import java.util.Optional;

public class ReservaServiceImpl implements ReservaService {

        private final ReservaRepository reservaRepository;

        public ReservaServiceImpl(ReservaRepository reservaRepository){
            this.reservaRepository = reservaRepository;
        }

        @Override
        public Reserva crearReserva(Reserva reserva){
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


