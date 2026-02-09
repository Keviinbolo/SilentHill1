package com.Kevin.SilentHill1.Service;

import com.Kevin.SilentHill1.DTO.ObjetoDTO;
import com.Kevin.SilentHill1.Entities.Objeto;
import com.Kevin.SilentHill1.Repository.ObjetoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ObjetoService {

    @Autowired
    private ObjetoRepo objetoRepository;

    // Convertir Entity → DTO
    private ObjetoDTO toDTO(Objeto objeto) {
        return new ObjetoDTO(
                objeto.getId(),
                objeto.getNombre(),
                objeto.getTipo(),
                objeto.getDescripcion(),
                objeto.getEfecto(),
                objeto.getRareza()
        );
    }

    // Convertir DTO → Entity
    private Objeto toEntity(ObjetoDTO dto) {
        Objeto objeto = new Objeto();
        objeto.setId(dto.getId());
        objeto.setNombre(dto.getNombre());
        objeto.setTipo(dto.getTipo());
        objeto.setDescripcion(dto.getDescripcion());
        objeto.setEfecto(dto.getEfecto());
        objeto.setRareza(dto.getRareza());
        return objeto;
    }

    // CREATE
    public ObjetoDTO createObjeto(ObjetoDTO objetoDTO) {
        Objeto objeto = toEntity(objetoDTO);
        Objeto saved = objetoRepository.save(objeto);
        return toDTO(saved);
    }

    // GET ALL
    public List<ObjetoDTO> getAllObjetos() {
        return objetoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public ObjetoDTO getObjetoById(Long id) {
        Objeto objeto = objetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado con ID: " + id));
        return toDTO(objeto);
    }

    // GET BY NOMBRE
    public ObjetoDTO getObjetoByNombre(String nombre) {
        Objeto objeto = objetoRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado: " + nombre));
        return toDTO(objeto);
    }

    // GET BY TIPO
    public List<ObjetoDTO> getObjetosPorTipo(String tipo) {
        return objetoRepository.findByTipo(tipo).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY RAREZA
    public List<ObjetoDTO> getObjetosPorRareza(String rareza) {
        return objetoRepository.findByRareza(rareza).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // SEARCH
    public List<ObjetoDTO> buscarObjetos(String texto) {
        return objetoRepository.findByNombreContainingIgnoreCase(texto).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public ObjetoDTO updateObjeto(Long id, ObjetoDTO objetoDTO) {
        Objeto objeto = objetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Objeto no encontrado con ID: " + id));

        objeto.setNombre(objetoDTO.getNombre());
        objeto.setTipo(objetoDTO.getTipo());
        objeto.setDescripcion(objetoDTO.getDescripcion());
        objeto.setEfecto(objetoDTO.getEfecto());
        objeto.setRareza(objetoDTO.getRareza());

        Objeto updated = objetoRepository.save(objeto);
        return toDTO(updated);
    }

    // DELETE
    public void deleteObjeto(Long id) {
        if (!objetoRepository.existsById(id)) {
            throw new RuntimeException("Objeto no encontrado con ID: " + id);
        }
        objetoRepository.deleteById(id);
    }
}