package com.Sistem.UsuarioCanchaReserva.service;
import java.util.List;
import java.util.Optional;

import com.Sistem.UsuarioCanchaReserva.entities.Usuario;

public interface UsuarioService {
    Usuario crearUsuario(Usuario usuario);

    List<Usuario> listarUsuarios();

    Optional<Usuario> getForId(Long id);

    Usuario activarUsuario(Long id);

    Usuario desactivarUsuario(Long id);

}
