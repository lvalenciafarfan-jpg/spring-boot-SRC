package com.Sistem.UsuarioCanchaReserva.service;

import com.Sistem.UsuarioCanchaReserva.entities.Cancha;

import java.util.List;

public interface CanchaService {

    Cancha crearCancha(Cancha cancha);

    List<Cancha> listarCanchas();

    Cancha listarPorId(Long id);

    Cancha actualizarPrecioH(Long id, Long nuevoPrecio);

    Cancha cambiarDisponibilidad(Long id);
}
