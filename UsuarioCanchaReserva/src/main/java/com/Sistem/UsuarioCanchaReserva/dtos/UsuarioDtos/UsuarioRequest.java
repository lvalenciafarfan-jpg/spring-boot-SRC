package com.Sistem.UsuarioCanchaReserva.dtos.UsuarioDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UsuarioRequest {

    public UsuarioRequest(){}
      @NotBlank(message = "El nombre es obligatorio")
      private String nombre;
      @NotNull(message = "El correo es obligatorio")
      private String correo;
      @NotNull(message = "El numero es obligatorio")
      @Positive(message = "El numero debe ser positivo")
      private Long numero;
      @NotNull(message = "El estado del activo es obligatorio")
      private boolean activo;
}
