package com.Sistem.UsuarioCanchaReserva.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String correo;

    @NotNull
    private Long numero;

    @NotNull
    private boolean activo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;
}
