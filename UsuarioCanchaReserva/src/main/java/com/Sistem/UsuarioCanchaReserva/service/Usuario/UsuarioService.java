package com.Sistem.UsuarioCanchaReserva.service.Usuario;
import java.util.List;

import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioResponse;

public interface UsuarioService {

    List<UsuarioResponse> listarUsuarios();

    UsuarioResponse obtenerPorId(Long id);

    UsuarioResponse activarUsuario(Long id);

    UsuarioResponse desactivarUsuario(Long id);

}
