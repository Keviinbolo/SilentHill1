package com.Kevin.SilentHill1.Controllers;

import com.Kevin.SilentHill1.Entities.Personaje;
import com.Kevin.SilentHill1.Repository.PersonajeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/SilentHill1/personajes")
@CrossOrigin(origins = "*")
public class PersonajeController {

    @Autowired
    private PersonajeRepo personajeRepository;

    @GetMapping
    public List<Personaje> getAllPersonajes() {
        return personajeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> getPersonajeById(@PathVariable Long id) {
        Optional<Personaje> personaje = personajeRepository.findById(id);
        return personaje.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Personaje> getPersonajeByNombre(@PathVariable String nombre) {
        Optional<Personaje> personaje = personajeRepository.findByNombre(nombre);
        return personaje.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{texto}")
    public List<Personaje> buscarPersonajesPorNombre(@PathVariable String texto) {
        return personajeRepository.findByNombreContainingIgnoreCase(texto);
    }

    @GetMapping("/historia/{texto}")
    public List<Personaje> buscarPorTextoEnHistoria(@PathVariable String texto) {
        return personajeRepository.buscarPorTextoEnHistoria(texto);
    }

    @PostMapping
    public Personaje createPersonaje(@RequestBody Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> updatePersonaje(@PathVariable Long id, @RequestBody Personaje personajeDetails) {
        Optional<Personaje> optionalPersonaje = personajeRepository.findById(id);
        if (optionalPersonaje.isPresent()) {
            Personaje personaje = optionalPersonaje.get();
            personaje.setNombre(personajeDetails.getNombre());
            personaje.setHistoria(personajeDetails.getHistoria());
            return ResponseEntity.ok(personajeRepository.save(personaje));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable Long id) {
        if (personajeRepository.existsById(id)) {
            personajeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}