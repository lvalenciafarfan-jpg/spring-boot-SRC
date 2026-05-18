package com.Sistem.UsuarioCanchaReserva.repository;

import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByUsuario(Usuario usuario);

    @Query("""
    SELECT COUNT(r) > 0 FROM Reserva r
    WHERE r.cancha.id = :canchaId
    AND r.estado = 'ACTIVA'
    AND (:inicio < r.horaFinal AND :fin > r.horaInicio)
    """)
    boolean existeConflicto(
            Long canchaId,
            LocalTime inicio,
            LocalTime fin
    );
}