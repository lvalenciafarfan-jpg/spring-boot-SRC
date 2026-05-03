package com.Sistem.UsuarioCanchaReserva.service.Usuario;
import java.util.List;

import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioResponse;

public interface UsuarioService {
    UsuarioResponse crearUsuario(UsuarioRequest usuario);

    List<UsuarioResponse> listarUsuarios();

    UsuarioResponse getForId(Long id);

    UsuarioResponse activarUsuario(Long id);

    UsuarioResponse desactivarUsuario(Long id);

}
