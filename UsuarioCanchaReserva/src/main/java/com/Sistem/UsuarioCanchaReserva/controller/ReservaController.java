package com.Sistem.UsuarioCanchaReserva.controller;

import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.ReservaDtos.ReservaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Reserva;
import com.Sistem.UsuarioCanchaReserva.entities.Usuario;
import com.Sistem.UsuarioCanchaReserva.service.Reserva.ReservaService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService){
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaResponse> crear(@Valid @RequestBody ReservaRequest reserva){
        ReservaResponse response = reservaService.crearReserva(reserva);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<ReservaResponse>> listar(@AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(reservaService.listarReservas(usuario));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponse> obtener(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
        ReservaResponse response = reservaService.listarPorId(id, usuario);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponse> cancelar(@PathVariable Long id){
        ReservaResponse response = reservaService.cancelarReserva(id);
        return ResponseEntity.ok(response);
    }
}