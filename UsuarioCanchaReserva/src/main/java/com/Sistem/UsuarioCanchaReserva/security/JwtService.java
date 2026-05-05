package com.Sistem.UsuarioCanchaReserva.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    // La clave secreta viene de application.properties, nunca hardcodeada
    @Value("${jwt.secret}")
    private String secretKey;

    // Genera un token con el correo del usuario adentro
    public String generarToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())       // guarda el correo
                .issuedAt(new Date())                     // cuándo se creó
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // expira en 24hs
                .signWith(getSigningKey())                // lo firma con tu clave secreta
                .compact();
    }

    // Valida que el token sea correcto y no haya expirado
    public boolean esValido(String token, UserDetails userDetails) {
        String correo = extraerCorreo(token);
        return correo.equals(userDetails.getUsername()) && !estaExpirado(token);
    }

    // Extrae el correo que está guardado dentro del token
    public String extraerCorreo(String token) {
        return extraerClaims(token).getSubject();
    }

    private boolean estaExpirado(String token) {
        return extraerClaims(token).getExpiration().before(new Date());
    }

    private Claims extraerClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}