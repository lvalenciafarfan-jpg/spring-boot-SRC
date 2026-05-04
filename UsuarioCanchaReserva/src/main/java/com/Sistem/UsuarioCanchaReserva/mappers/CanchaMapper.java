package com.Sistem.UsuarioCanchaReserva.mappers;

import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;

public class CanchaMapper {

    public static Cancha toEntity(CanchaRequest datos){
        Cancha cancha = new Cancha();
        cancha.setNombre(datos.getNombre());
        cancha.setTipo(datos.getTipo());
        cancha.setPrecioxhora(datos.getPrecioPorHora());
        cancha.setDisponible(true);

        return cancha;
    }

    public static CanchaResponse ResponseDtos(Cancha datos){
        CanchaResponse canchaResponse = new CanchaResponse();
        canchaResponse.setTipo(datos.getTipo());
        canchaResponse.setNombre(datos.getNombre());
        canchaResponse.setPrecioPorHora(datos.getPrecioxhora());
        canchaResponse.setDisponibilidad(true);

        return canchaResponse;
    }

}
