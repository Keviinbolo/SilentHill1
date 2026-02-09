package com.Kevin.SilentHill1.Service;

import com.Kevin.SilentHill1.DTO.EnemigoDTO;
import com.Kevin.SilentHill1.Entities.Enemigo;
import com.Kevin.SilentHill1.Entities.Ubicacion;
import com.Kevin.SilentHill1.Repository.EnemigoRepo;
import com.Kevin.SilentHill1.Repository.UbicacionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnemigoService {

    @Autowired
    private EnemigoRepo enemigoRepository;

    @Autowired
    private UbicacionRepo ubicacionRepository;

    // Convertir Entity → DTO
    private EnemigoDTO toDTO(Enemigo enemigo) {
        EnemigoDTO dto = new EnemigoDTO(
                enemigo.getId(),
                enemigo.getNombre(),
                enemigo.getTipo(),
                enemigo.getDebilidad()
        );

        if (enemigo.getUbicacion() != null) {
            dto.setUbicacionId(enemigo.getUbicacion().getId());
        }

        return dto;
    }

    // Convertir DTO → Entity (sin ubicación todavía)
    private Enemigo toEntity(EnemigoDTO dto) {
        Enemigo enemigo = new Enemigo();
        enemigo.setId(dto.getId());
        enemigo.setNombre(dto.getNombre());
        enemigo.setTipo(dto.getTipo());
        enemigo.setDebilidad(dto.getDebilidad());
        return enemigo;
    }

    // CREATE con ubicación
    public EnemigoDTO createEnemigo(EnemigoDTO enemigoDTO) {
        Enemigo enemigo = toEntity(enemigoDTO);

        // Asignar ubicación si se proporciona ID
        if (enemigoDTO.getUbicacionId() != null) {
            Ubicacion ubicacion = ubicacionRepository.findById(enemigoDTO.getUbicacionId())
                    .orElseThrow(() -> new RuntimeException("Ubicación no encontrada con ID: " + enemigoDTO.getUbicacionId()));
            enemigo.setUbicacion(ubicacion);
        }

        Enemigo saved = enemigoRepository.save(enemigo);
        return toDTO(saved);
    }

    // GET ALL
    public List<EnemigoDTO> getAllEnemigos() {
        return enemigoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public EnemigoDTO getEnemigoById(Long id) {
        Enemigo enemigo = enemigoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enemigo no encontrado con ID: " + id));
        return toDTO(enemigo);
    }

    // GET BY NOMBRE
    public EnemigoDTO getEnemigoByNombre(String nombre) {
        Enemigo enemigo = enemigoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Enemigo no encontrado: " + nombre));
        return toDTO(enemigo);
    }

    // GET BY TIPO
    public List<EnemigoDTO> getEnemigosPorTipo(String tipo) {
        return enemigoRepository.findByTipo(tipo).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY DEBILIDAD
    public List<EnemigoDTO> getEnemigosPorDebilidad(String debilidad) {
        return enemigoRepository.findByDebilidad(debilidad).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // SEARCH
    public List<EnemigoDTO> buscarEnemigos(String texto) {
        return enemigoRepository.findByNombreContainingIgnoreCase(texto).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public EnemigoDTO updateEnemigo(Long id, EnemigoDTO enemigoDTO) {
        Enemigo enemigo = enemigoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enemigo no encontrado con ID: " + id));

        enemigo.setNombre(enemigoDTO.getNombre());
        enemigo.setTipo(enemigoDTO.getTipo());
        enemigo.setDebilidad(enemigoDTO.getDebilidad());

        // Actualizar ubicación si se proporciona nuevo ID
        if (enemigoDTO.getUbicacionId() != null) {
            Ubicacion ubicacion = ubicacionRepository.findById(enemigoDTO.getUbicacionId())
                    .orElseThrow(() -> new RuntimeException("Ubicación no encontrada con ID: " + enemigoDTO.getUbicacionId()));
            enemigo.setUbicacion(ubicacion);
        }

        Enemigo updated = enemigoRepository.save(enemigo);
        return toDTO(updated);
    }

    // DELETE
    public void deleteEnemigo(Long id) {
        if (!enemigoRepository.existsById(id)) {
            throw new RuntimeException("Enemigo no encontrado con ID: " + id);
        }
        enemigoRepository.deleteById(id);
    }
}