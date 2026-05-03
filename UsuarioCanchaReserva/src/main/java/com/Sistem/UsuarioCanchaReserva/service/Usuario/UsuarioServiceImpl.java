package com.Sistem.UsuarioCanchaReserva.service.Usuario;
import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioResponse;
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

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request){
        Usuario usuario = new Usuario();
        usuario.setNombre(usuario.getNombre());
        usuario.setCorreo(usuario.getCorreo());
        usuario.setNumero(usuario.getNumero());
        usuario.setActivo(usuario.isActivo());

        Usuario guardado = usuarioRepository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setId(guardado.getId());
        response.setNombre(guardado.getNombre());
        response.setCorreo(guardado.getCorreo());
        response.setNumero(guardado.getNumero());
        response.setActivo(guardado.isActivo());

        return response;

    }

    @Override
    public List<UsuarioResponse> listarUsuarios(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioResponse> listaDTO = usuarios.stream()
                .map(usuario -> {
                    UsuarioResponse dto = new UsuarioResponse();
                    dto.setId(usuario.getId());
                    dto.setNombre(usuario.getNombre());
                    dto.setCorreo(usuario.getCorreo());
                    dto.setNumero(usuario.getNumero());
                    dto.setActivo(usuario.isActivo());
                    return dto;
                }).toList();

        return listaDTO;
    }

    @Override
    public UsuarioResponse getForId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreo(usuario.getCorreo());
        dto.setNumero(usuario.getNumero());
        dto.setActivo(usuario.isActivo());

        return dto;
    }

    @Override
    public UsuarioResponse activarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(usuario.isActivo()){
            throw new IllegalArgumentException("No se puede activar un usuario ya activo.");
        }

        usuario.setActivo(true);

        Usuario guardado = usuarioRepository.save(usuario);

        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(guardado.getId());
        dto.setNombre(guardado.getNombre());
        dto.setCorreo(guardado.getCorreo());
        dto.setNumero(guardado.getNumero());
        dto.setActivo(guardado.isActivo());

        return dto;
    }

    @Override
    public UsuarioResponse desactivarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(!(usuario.isActivo())){
            throw new IllegalArgumentException("No se puede desactivar un usuario ya desactivado.");
        }

        usuario.setActivo(false);
        Usuario guardado = usuarioRepository.save(usuario);

        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(guardado.getId());
        dto.setNombre(guardado.getNombre());
        dto.setCorreo(guardado.getCorreo());
        dto.setNumero(guardado.getNumero());
        dto.setActivo(guardado.isActivo());

        return dto;
    }
}
