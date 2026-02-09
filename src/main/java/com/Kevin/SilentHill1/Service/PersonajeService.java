package com.Kevin.SilentHill1.Service;

import com.Kevin.SilentHill1.DTO.PersonajeDTO;
import com.Kevin.SilentHill1.Entities.Personaje;
import com.Kevin.SilentHill1.Repository.PersonajeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonajeService {

    @Autowired
    private PersonajeRepo personajeRepository;

    // Convertir Entity → DTO
    private PersonajeDTO toDTO(Personaje personaje) {
        return new PersonajeDTO(
                personaje.getId(),
                personaje.getNombre(),
                personaje.getHistoria()
        );
    }

    // Convertir DTO → Entity
    private Personaje toEntity(PersonajeDTO dto) {
        Personaje personaje = new Personaje();
        personaje.setId(dto.getId());
        personaje.setNombre(dto.getNombre());
        personaje.setHistoria(dto.getHistoria());
        return personaje;
    }

    // CREATE
    public PersonajeDTO createPersonaje(PersonajeDTO personajeDTO) {
        Personaje personaje = toEntity(personajeDTO);
        Personaje saved = personajeRepository.save(personaje);
        return toDTO(saved);
    }

    // GET ALL
    public List<PersonajeDTO> getAllPersonajes() {
        return personajeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // GET por ID
    public PersonajeDTO getPersonajeById(Long id) {
        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado con ID: " + id));
        return toDTO(personaje);
    }

    // GET por NOMBRE
    public PersonajeDTO getPersonajeByNombre(String nombre) {
        Personaje personaje = personajeRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado: " + nombre));
        return toDTO(personaje);
    }

    // Busqueda por texto en nombre
    public List<PersonajeDTO> buscarPersonajes(String texto) {
        return personajeRepository.findByNombreContainingIgnoreCase(texto).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    public PersonajeDTO updatePersonaje(Long id, PersonajeDTO personajeDTO) {
        Personaje personaje = personajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado con ID: " + id));

        personaje.setNombre(personajeDTO.getNombre());
        personaje.setHistoria(personajeDTO.getHistoria());

        Personaje updated = personajeRepository.save(personaje);
        return toDTO(updated);
    }

    // DELETE
    public void deletePersonaje(Long id) {
        if (!personajeRepository.existsById(id)) {
            throw new RuntimeException("Personaje no encontrado con ID: " + id);
        }
        personajeRepository.deleteById(id);
    }
}