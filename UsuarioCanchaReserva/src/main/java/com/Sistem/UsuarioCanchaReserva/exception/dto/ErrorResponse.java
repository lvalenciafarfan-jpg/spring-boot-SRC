package com.Sistem.UsuarioCanchaReserva.exception.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String mensaje;
    private LocalDateTime timestap;
}
