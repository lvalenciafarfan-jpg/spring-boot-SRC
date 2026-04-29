package com.Sistem.UsuarioCanchaReserva.serviceImpl;
import org.springframework.stereotype.Service;

import com.Sistem.UsuarioCanchaReserva.entities.Usuario;
import com.Sistem.UsuarioCanchaReserva.repository.UsuarioRepository;
import com.Sistem.UsuarioCanchaReserva.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getForId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario activarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(usuario.isActivo()){
            throw new IllegalArgumentException("No se puede activar un usuario ya activo.");
        }

        usuario.setActivo(true);

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario desactivarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(!(usuario.isActivo())){
            throw new IllegalArgumentException("No se puede desactivar un usuario ya desactivado.");
        }

        usuario.setActivo(false);

        return usuarioRepository.save(usuario);
    }
}
