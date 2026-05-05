package com.Sistem.UsuarioCanchaReserva.service.Auth;

import com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos.LoginRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos.LoginResponse;
import com.Sistem.UsuarioCanchaReserva.dtos.AuthDtos.RegistroRequest;

public interface AuthService {
    void registro(RegistroRequest request);
    LoginResponse login(LoginRequest request);
}
