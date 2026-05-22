package com.Sistem.UsuarioCanchaReserva.repository;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, Long>{

    Page<Cancha> findAll(Pageable pageable);
}
