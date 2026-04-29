package com.Sistem.UsuarioCanchaReserva.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;


@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String correo;

    @NotNull
    private int numero;

    @NotNull
    private boolean activo;
}
