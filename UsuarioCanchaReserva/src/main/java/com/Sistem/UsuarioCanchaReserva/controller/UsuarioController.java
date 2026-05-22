package com.Sistem.UsuarioCanchaReserva.controller;

import com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos.UsuarioResponse;
import com.Sistem.UsuarioCanchaReserva.service.Usuario.UsuarioService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> listar(
            @PageableDefault (size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        return ResponseEntity.ok(usuarioService.listarUsuarios(pageable));
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
