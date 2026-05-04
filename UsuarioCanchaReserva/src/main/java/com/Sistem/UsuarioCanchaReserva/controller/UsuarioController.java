package com.Sistem.UsuarioCanchaReserva.controller;

import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioResponse;
import com.Sistem.UsuarioCanchaReserva.service.Usuario.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(@RequestBody UsuarioRequest usuario){
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar(){

        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public UsuarioResponse obtener(@PathVariable Long id){
        return usuarioService.obtenerPorId(id);
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<UsuarioResponse> activar(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.activarUsuario(id));
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<UsuarioResponse> desactivar(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.desactivarUsuario(id));
    }
}
