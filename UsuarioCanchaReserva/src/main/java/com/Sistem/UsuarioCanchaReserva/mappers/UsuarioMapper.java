package com.Sistem.UsuarioCanchaReserva.mappers;

import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;

public class UsuarioMapper {

    public static UsuarioResponse toResponse(Usuario datos) {
        UsuarioResponse user = new UsuarioResponse();
        user.setNombre(datos.getNombre());
        user.setCorreo(datos.getCorreo());
        user.setNumero(datos.getNumero());
        user.setActivo(datos.isActivo());

        return user;
    }

}