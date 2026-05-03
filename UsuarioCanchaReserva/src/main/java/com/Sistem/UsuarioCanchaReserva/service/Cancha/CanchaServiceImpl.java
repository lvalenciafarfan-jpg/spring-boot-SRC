package com.Sistem.UsuarioCanchaReserva.service.Cancha;

import com.Sistem.UsuarioCanchaReserva.entities.Cancha;
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
    public Cancha crearCancha(Cancha cancha) {
        return canchaRepository.save(cancha);
    }

    @Override
    public List<Cancha> listarCanchas() {
        return canchaRepository.findAll();
    }

    @Override
    public Cancha listarPorId(Long id) {
        return canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));
    }

    @Override
    public Cancha actualizarPrecioH(Long id, Long nuevoPrecio) {
        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        if(nuevoPrecio <=0){
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }

        cancha.setPrecioxhora(nuevoPrecio);
        return canchaRepository.save(cancha);
    }

    @Override
    public Cancha cambiarDisponibilidad(Long id) {
        Cancha cancha = canchaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cancha no encontrada"));

        cancha.setDisponible(!cancha.isDisponible());

        return canchaRepository.save(cancha);
    }
}
