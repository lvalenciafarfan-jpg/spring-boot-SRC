package com.Sistem.UsuarioCanchaReserva.service.Cancha;

import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CanchaService {

    CanchaResponse crearCancha(CanchaRequest cancha);

    Page<CanchaResponse> listarCanchas(Pageable pageable);

    CanchaResponse listarPorId(Long id);

    CanchaResponse actualizarPrecioH(Long id, Long nuevoPrecio);

    CanchaResponse cambiarDisponibilidad(Long id);
}
