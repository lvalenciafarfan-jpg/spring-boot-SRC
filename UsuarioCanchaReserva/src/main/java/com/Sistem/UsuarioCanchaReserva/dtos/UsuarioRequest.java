package com.Sistem.UsuarioCanchaReserva.dtos;

import lombok.Data;

@Data
public class UsuarioRequest {

    public UsuarioRequest(){}

      private String nombre;
      private String correo;
      private Long numero;
      private boolean activo;
}
