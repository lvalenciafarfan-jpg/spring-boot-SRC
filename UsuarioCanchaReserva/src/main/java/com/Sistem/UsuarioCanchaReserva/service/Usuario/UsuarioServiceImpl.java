package com.Sistem.UsuarioCanchaReserva.service.Usuario;
import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioResponse;
import com.Sistem.UsuarioCanchaReserva.exception.customs.RecursoNoEncontradoException;
import com.Sistem.UsuarioCanchaReserva.exception.customs.ReglaNegocioException;
import com.Sistem.UsuarioCanchaReserva.mappers.UsuarioMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Sistem.UsuarioCanchaReserva.entities.Usuario;
import com.Sistem.UsuarioCanchaReserva.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    private Usuario obtenerUsuario(Long id){
        return usuarioRepository.findById(id).orElseThrow(() ->
                new RecursoNoEncontradoException("El usuario con id: " + id + " no ha sido encontrado"));
    }


    @Override
    public Page<UsuarioResponse> listarUsuarios(Pageable pageable){
        return usuarioRepository.findAll(pageable)
                .map(UsuarioMapper::toResponse);
    }

    @Override
    public UsuarioResponse obtenerPorId(Long id) {
        Usuario usuario = obtenerUsuario(id);

        return UsuarioMapper.toResponse(usuario);

    }

    @Override
    public UsuarioResponse activarUsuario(Long id){
        Usuario usuario = obtenerUsuario(id);

        if(usuario.isActivo()){
            throw new ReglaNegocioException("No se puede activar un usuario ya activo.");
        }

        usuario.setActivo(true);

        Usuario guardado = usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(guardado);
    }

    @Override
    public UsuarioResponse desactivarUsuario(Long id){
        Usuario usuario = obtenerUsuario(id);

        if(!(usuario.isActivo())){
            throw new ReglaNegocioException("No se puede desactivar un usuario ya desactivado.");
        }

        usuario.setActivo(false);
        Usuario guardado = usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(guardado);
    }
}
