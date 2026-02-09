package com.Kevin.SilentHill1.Service;

import com.Kevin.SilentHill1.DTO.UbicacionDTO;
import com.Kevin.SilentHill1.Entities.Ubicacion;
import com.Kevin.SilentHill1.Repository.UbicacionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UbicacionService {

    @Autowired
    private UbicacionRepo ubicacionRepository;

    // Convertir Entity → DTO
    private UbicacionDTO toDTO(Ubicacion ubicacion) {
        return new UbicacionDTO(
                ubicacion.getId(),
                ubicacion.getNombre(),
                ubicacion.getTipo(),
                ubicacion.getDescripcion(),
                ubicacion.getPeligroNivel()
        );
    }

    // Convertir DTO → Entity
    private Ubicacion toEntity(UbicacionDTO dto) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setId(dto.getId());
        ubicacion.setNombre(dto.getNombre());
        ubicacion.setTipo(dto.getTipo());
        ubicacion.setDescripcion(dto.getDescripcion());
        ubicacion.setPeligroNivel(dto.getPeligroNivel());
        return ubicacion;
    }

    // CREATE
    public UbicacionDTO createUbicacion(UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacion = toEntity(ubicacionDTO);
        Ubicacion saved = ubicacionRepository.save(ubicacion);
        return toDTO(saved);
    }

    // GET ALL
    public List<UbicacionDTO> getAllUbicaciones() {
        return ubicacionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public UbicacionDTO getUbicacionById(Long id) {
        Ubicacion ubicacion = ubicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada con ID: " + id));
        return toDTO(ubicacion);
    }

    // GET BY NOMBRE
    public UbicacionDTO getUbicacionByNombre(String nombre) {
        Ubicacion ubicacion = ubicacionRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada: " + nombre));
        return toDTO(ubicacion);
    }

    // GET BY TIPO
    public List<UbicacionDTO> getUbicacionesPorTipo(String tipo) {
        return ubicacionRepository.findByTipo(tipo).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY PELIGRO
    public List<UbicacionDTO> getUbicacionesPorPeligro(Integer nivel) {
        return ubicacionRepository.findByPeligroNivelGreaterThanEqual(nivel).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // SEARCH
    public List<UbicacionDTO> buscarUbicaciones(String texto) {
        return ubicacionRepository.findByNombreContainingIgnoreCase(texto).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public UbicacionDTO updateUbicacion(Long id, UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacion = ubicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada con ID: " + id));

        ubicacion.setNombre(ubicacionDTO.getNombre());
        ubicacion.setTipo(ubicacionDTO.getTipo());
        ubicacion.setDescripcion(ubicacionDTO.getDescripcion());
        ubicacion.setPeligroNivel(ubicacionDTO.getPeligroNivel());

        Ubicacion updated = ubicacionRepository.save(ubicacion);
        return toDTO(updated);
    }

    // DELETE
    public void deleteUbicacion(Long id) {
        if (!ubicacionRepository.existsById(id)) {
            throw new RuntimeException("Ubicación no encontrada con ID: " + id);
        }
        ubicacionRepository.deleteById(id);
    }
}