package com.Sistem.UsuarioCanchaReserva.repository;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, Long>{
}
