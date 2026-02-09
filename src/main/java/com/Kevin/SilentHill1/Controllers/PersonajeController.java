package com.Kevin.SilentHill1.Controllers;

import com.Kevin.SilentHill1.DTO.PersonajeDTO;
import com.Kevin.SilentHill1.Service.PersonajeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personajes")
@CrossOrigin(origins = "*")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getAllPersonajes() {
        List<PersonajeDTO> personajes = personajeService.getAllPersonajes();
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getPersonajeById(@PathVariable Long id) {
        PersonajeDTO personaje = personajeService.getPersonajeById(id);
        return ResponseEntity.ok(personaje);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<PersonajeDTO> getPersonajeByNombre(@PathVariable String nombre) {
        PersonajeDTO personaje = personajeService.getPersonajeByNombre(nombre);
        return ResponseEntity.ok(personaje);
    }

    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<PersonajeDTO>> buscarPersonajes(@PathVariable String texto) {
        List<PersonajeDTO> personajes = personajeService.buscarPersonajes(texto);
        return ResponseEntity.ok(personajes);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> createPersonaje(@Valid @RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO created = personajeService.createPersonaje(personajeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> updatePersonaje(
            @PathVariable Long id,
            @Valid @RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO updated = personajeService.updatePersonaje(id, personajeDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable Long id) {
        personajeService.deletePersonaje(id);
        return ResponseEntity.noContent().build();
    }
}