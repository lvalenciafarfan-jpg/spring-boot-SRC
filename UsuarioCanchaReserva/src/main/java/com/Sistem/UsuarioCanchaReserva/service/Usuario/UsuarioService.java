package com.Sistem.UsuarioCanchaReserva.service.Usuario;
import java.util.List;

import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Page<UsuarioResponse> listarUsuarios(Pageable pageable);

    UsuarioResponse obtenerPorId(Long id);

    UsuarioResponse activarUsuario(Long id);

    UsuarioResponse desactivarUsuario(Long id);

}
