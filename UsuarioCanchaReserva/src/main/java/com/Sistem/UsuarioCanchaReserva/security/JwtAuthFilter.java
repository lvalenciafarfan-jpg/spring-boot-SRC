package com.Sistem.UsuarioCanchaReserva.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioDetailsService usuarioDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Busca el header "Authorization" en el request
        String authHeader = request.getHeader("Authorization");

        // 2. Si no tiene token o no empieza con "Bearer ", lo deja pasar sin autenticar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extrae el token quitando el "Bearer " del inicio
        String token = authHeader.substring(7);

        // 4. Extrae el correo que está dentro del token
        String correo = jwtService.extraerCorreo(token);

        // 5. Si hay correo y el usuario aún no está autenticado en esta request
        if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Busca el usuario en BD
            UserDetails userDetails = usuarioDetailsService.loadUserByUsername(correo);

            // 7. Valida que el token sea correcto
            if (jwtService.esValido(token, userDetails)) {

                // 8. Le dice a Spring que este usuario está autenticado
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 9. Continúa al controller
        filterChain.doFilter(request, response);
    }
}