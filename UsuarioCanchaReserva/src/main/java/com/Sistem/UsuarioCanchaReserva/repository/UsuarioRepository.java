package com.Sistem.UsuarioCanchaReserva.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Page<Usuario> findAll(Pageable pageable);
    Optional<Usuario> findByCorreo(String correo);
}