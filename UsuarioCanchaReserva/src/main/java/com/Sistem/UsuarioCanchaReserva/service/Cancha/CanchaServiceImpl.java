package com.Sistem.UsuarioCanchaReserva.service.Cancha;

import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import com.Sistem.UsuarioCanchaReserva.mappers.CanchaMapper;
import com.Sistem.UsuarioCanchaReserva.repository.CanchaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanchaServiceImpl implements CanchaService {

    private final CanchaRepository canchaRepository;

    public CanchaServiceImpl(CanchaRepository canchaRepository){
        this.canchaRepository = canchaRepository;
    }

    @Override
    public CanchaResponse crearCancha(CanchaRequest cancha) {
        Cancha guardado = CanchaMapper.toEntity(cancha);
        canchaRepository.save(guardado);

        return CanchaMapper.ResponseDtos(guardado);
    }

    @Override
    public List<CanchaResponse> listarCanchas() {
        List<Cancha> canchas = canchaRepository.findAll();

        return canchas.stream()
                .map(CanchaMapper::ResponseDtos)
                .toList();
    }

    @Override
    public CanchaResponse listarPorId(Long id) {
        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        return CanchaMapper.ResponseDtos(cancha);
    }

    @Override
    public CanchaResponse actualizarPrecioH(Long id, Long nuevoPrecio) {
        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        if(nuevoPrecio <=0){
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }

        cancha.setPrecioxhora(nuevoPrecio);

        return CanchaMapper.ResponseDtos(cancha);
    }

    @Override
    public CanchaResponse cambiarDisponibilidad(Long id) {
        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        cancha.setDisponible(!cancha.isDisponible());

        return CanchaMapper.ResponseDtos(cancha);
    }
}
