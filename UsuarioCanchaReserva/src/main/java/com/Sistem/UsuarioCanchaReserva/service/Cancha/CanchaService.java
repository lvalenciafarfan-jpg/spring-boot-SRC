package com.Sistem.UsuarioCanchaReserva.service.Cancha;

import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;

import java.util.List;

public interface CanchaService {

    CanchaResponse crearCancha(CanchaRequest cancha);

    List<CanchaResponse> listarCanchas();

    CanchaResponse listarPorId(Long id);

    CanchaResponse actualizarPrecioH(Long id, Long nuevoPrecio);

    CanchaResponse cambiarDisponibilidad(Long id);
}
