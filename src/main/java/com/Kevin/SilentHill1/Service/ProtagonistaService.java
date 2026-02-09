package com.Kevin.SilentHill1.Service;

import com.Kevin.SilentHill1.DTO.ProtagonistaDTO;
import com.Kevin.SilentHill1.Entities.Personaje;
import com.Kevin.SilentHill1.Entities.Protagonista;
import com.Kevin.SilentHill1.Repository.PersonajeRepo;
import com.Kevin.SilentHill1.Repository.ProtagonistaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProtagonistaService {

    @Autowired
    private ProtagonistaRepo protagonistaRepository;

    @Autowired
    private PersonajeRepo personajeRepository;

    // Convertir Entity → DTO
    private ProtagonistaDTO toDTO(Protagonista protagonista) {
        ProtagonistaDTO dto = new ProtagonistaDTO(
                protagonista.getId(),
                protagonista.getSalud(),
                protagonista.getInventario(),
                protagonista.getArmaActual(),
                protagonista.getNivelDificultad()
        );

        if (protagonista.getPersonaje() != null) {
            dto.setPersonajeId(protagonista.getPersonaje().getId());
        }

        return dto;
    }

    // Convertir DTO → Entity (sin personaje todavía)
    private Protagonista toEntity(ProtagonistaDTO dto) {
        Protagonista protagonista = new Protagonista();
        protagonista.setId(dto.getId());
        protagonista.setSalud(dto.getSalud());
        protagonista.setInventario(dto.getInventario());
        protagonista.setArmaActual(dto.getArmaActual());
        protagonista.setNivelDificultad(dto.getNivelDificultad());
        return protagonista;
    }

    // CREATE con personaje
    public ProtagonistaDTO createProtagonista(ProtagonistaDTO protagonistaDTO) {
        Protagonista protagonista = toEntity(protagonistaDTO);

        // Asignar personaje si se proporciona ID
        if (protagonistaDTO.getPersonajeId() != null) {
            Personaje personaje = personajeRepository.findById(protagonistaDTO.getPersonajeId())
                    .orElseThrow(() -> new RuntimeException("Personaje no encontrado con ID: " + protagonistaDTO.getPersonajeId()));
            protagonista.setPersonaje(personaje);
        }

        Protagonista saved = protagonistaRepository.save(protagonista);
        return toDTO(saved);
    }

    // GET ALL
    public List<ProtagonistaDTO> getAllProtagonistas() {
        return protagonistaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public ProtagonistaDTO getProtagonistaById(Long id) {
        Protagonista protagonista = protagonistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Protagonista no encontrado con ID: " + id));
        return toDTO(protagonista);
    }

    // GET BY DIFICULTAD
    public List<ProtagonistaDTO> getProtagonistasPorDificultad(String nivel) {
        return protagonistaRepository.findByNivelDificultad(nivel).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY SALUD MAYOR QUE
    public List<ProtagonistaDTO> getProtagonistasConSaludMayor(Integer salud) {
        return protagonistaRepository.findBySaludGreaterThan(salud).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ARMA
    public List<ProtagonistaDTO> getProtagonistasPorArma(String arma) {
        return protagonistaRepository.findByArmaActual(arma).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public ProtagonistaDTO updateProtagonista(Long id, ProtagonistaDTO protagonistaDTO) {
        Protagonista protagonista = protagonistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Protagonista no encontrado con ID: " + id));

        protagonista.setSalud(protagonistaDTO.getSalud());
        protagonista.setInventario(protagonistaDTO.getInventario());
        protagonista.setArmaActual(protagonistaDTO.getArmaActual());
        protagonista.setNivelDificultad(protagonistaDTO.getNivelDificultad());

        // Actualizar personaje si se proporciona nuevo ID
        if (protagonistaDTO.getPersonajeId() != null) {
            Personaje personaje = personajeRepository.findById(protagonistaDTO.getPersonajeId())
                    .orElseThrow(() -> new RuntimeException("Personaje no encontrado con ID: " + protagonistaDTO.getPersonajeId()));
            protagonista.setPersonaje(personaje);
        }

        Protagonista updated = protagonistaRepository.save(protagonista);
        return toDTO(updated);
    }

    // DELETE
    public void deleteProtagonista(Long id) {
        if (!protagonistaRepository.existsById(id)) {
            throw new RuntimeException("Protagonista no encontrado con ID: " + id);
        }
        protagonistaRepository.deleteById(id);
    }
}