package com.Sistem.UsuarioCanchaReserva.service.Cancha;

import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaRequest;
import com.Sistem.UsuarioCanchaReserva.dtos.CanchaDtos.CanchaResponse;
import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
import com.Sistem.UsuarioCanchaReserva.exception.customs.RecursoNoEncontradoException;
import com.Sistem.UsuarioCanchaReserva.exception.customs.ReglaNegocioException;
import com.Sistem.UsuarioCanchaReserva.mappers.CanchaMapper;
import com.Sistem.UsuarioCanchaReserva.repository.CanchaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanchaServiceImpl implements CanchaService {

    private final CanchaRepository canchaRepository;

    public CanchaServiceImpl(CanchaRepository canchaRepository){
        this.canchaRepository = canchaRepository;
    }


    private Cancha obtenerCancha(Long id){
        Cancha cancha = canchaRepository.findById(id).orElseThrow(()
                -> new RecursoNoEncontradoException("La cancha con id: " + id + " no ha sido encontrada"));
        return cancha;
    }

    @Override
    public CanchaResponse crearCancha(@Valid CanchaRequest cancha) {
        Cancha guardado = CanchaMapper.toEntity(cancha);
        canchaRepository.save(guardado);

        return CanchaMapper.toResponse(guardado);
    }

    @Override
    public List<CanchaResponse> listarCanchas() {
        List<Cancha> canchas = canchaRepository.findAll();

        return canchas.stream()
                .map(CanchaMapper::toResponse)
                .toList();
    }

    @Override
    public CanchaResponse listarPorId(Long id) {
        Cancha cancha = obtenerCancha(id);

        return CanchaMapper.toResponse(cancha);
    }

    @Override
    public CanchaResponse actualizarPrecioH(Long id, Long nuevoPrecio) {

        if(nuevoPrecio <=0){
            throw new ReglaNegocioException("El nuevo precio debe ser mayor a 0.");
        }

        Cancha cancha = obtenerCancha(id);

        cancha.setPrecioxhora(nuevoPrecio);

        canchaRepository.save(cancha);

        return CanchaMapper.toResponse(cancha);
    }

    @Override
    public CanchaResponse cambiarDisponibilidad(Long id) {
        Cancha cancha = obtenerCancha(id);

        cancha.setDisponible(!cancha.isDisponible());

        canchaRepository.save(cancha);

        return CanchaMapper.toResponse(cancha);
    }
}
