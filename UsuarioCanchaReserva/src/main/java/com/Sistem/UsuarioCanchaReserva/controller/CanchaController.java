package com.Sistem.UsuarioCanchaReserva.controller;

import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import com.Sistem.UsuarioCanchaReserva.service.CanchaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canchas")
public class CanchaController {

    private final CanchaService canchaService;

    public CanchaController(CanchaService canchaService){
        this.canchaService = canchaService;
    }

    @PostMapping
    public ResponseEntity<Cancha> crear(@RequestBody Cancha cancha){
        return ResponseEntity.ok(canchaService.crearCancha(cancha));
    }

    @GetMapping
    public ResponseEntity<List<Cancha>> listar(){
        return ResponseEntity.ok(canchaService.listarCanchas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancha> obtener(@PathVariable Long id){
        return ResponseEntity.ok(canchaService.listarPorId(id));
    }

    @PutMapping("/{id}/precio")
    public ResponseEntity<Cancha> actualizarPrecio(
            @PathVariable Long id,
            @RequestParam Long nuevoPrecio
    ){
        return ResponseEntity.ok(canchaService.actualizarPrecioH(id, nuevoPrecio));
    }

    @PutMapping("/{id}/disponibilidad")
    public ResponseEntity<Cancha> cambiarDisponibilidad(@PathVariable Long id){
        return ResponseEntity.ok(canchaService.cambiarDisponibilidad(id));
    }
}
