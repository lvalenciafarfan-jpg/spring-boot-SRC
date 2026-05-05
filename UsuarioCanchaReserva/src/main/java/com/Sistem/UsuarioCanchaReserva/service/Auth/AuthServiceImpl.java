package com.Sistem.UsuarioCanchaReserva.service.Auth;

import com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos.LoginRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos.LoginResponse;
import com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos.RegistroRequest;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;
import com.Sistem.UsuarioCanchaReserva.enums.Rol;
import com.Sistem.UsuarioCanchaReserva.exception.customs.ReglaNegocioException;
import com.Sistem.UsuarioCanchaReserva.repository.UsuarioRepository;
import com.Sistem.UsuarioCanchaReserva.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void registro(RegistroRequest request) {

        // Verificar que el correo no esté ya registrado
        if (usuarioRepository.findByCorreo(request.getCorreo()).isPresent()) {
            throw new ReglaNegocioException("Ya existe un usuario con ese correo");
        }

        // Construir el nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setNumero(request.getNumero());
        usuario.setPassword(passwordEncoder.encode(request.getPassword())); // hashea el password
        usuario.setRol(Rol.USER);    // por defecto siempre USER
        usuario.setActivo(true);     // activo al registrarse

        usuarioRepository.save(usuario);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        // Le dice a Spring que verifique correo y password
        // Si son incorrectos, lanza una excepción automáticamente
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Si llegó hasta aquí, las credenciales son correctas
        Usuario usuario = usuarioRepository.findByCorreo(request.getEmail())
                .orElseThrow();

        // Genera el token y lo devuelve
        String token = jwtService.generarToken(usuario);
        return new LoginResponse(token);
    }
}