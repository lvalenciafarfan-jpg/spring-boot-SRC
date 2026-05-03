package com.Sistem.UsuarioCanchaReserva.service;
import java.util.List;
import java.util.Optional;

import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;

public interface UsuarioService {
    UsuarioResponse crearUsuario(UsuarioRequest usuario);

    List<UsuarioResponse> listarUsuarios();

    UsuarioResponse getForId(Long id);

    UsuarioResponse activarUsuario(Long id);

    UsuarioResponse desactivarUsuario(Long id);

}
