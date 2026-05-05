package com.Sistem.UsuarioCanchaReserva.service.Usuario;
import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioResponse;
import com.Sistem.UsuarioCanchaReserva.exception.customs.RecursoNoEncontradoException;
import com.Sistem.UsuarioCanchaReserva.exception.customs.ReglaNegocioException;
import com.Sistem.UsuarioCanchaReserva.mappers.UsuarioMapper;
import jakarta.validation.Valid;
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
    public UsuarioResponse crearUsuario(@Valid UsuarioRequest request){
        Usuario usuario = UsuarioMapper.toEntity(request);

        usuario.setActivo(true);

        Usuario guardado = usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(guardado);
    }

    @Override
    public List<UsuarioResponse> listarUsuarios(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(UsuarioMapper::toResponse)
                .toList();
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
