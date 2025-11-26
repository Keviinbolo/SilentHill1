package com.Kevin.SilentHill1.Controllers;


import com.Kevin.SilentHill1.Entities.Protagonista;
import com.Kevin.SilentHill1.Repository.ProtagonistaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/protagonistas")
@CrossOrigin(origins = "*")
public class ProtagonistaController {

    @Autowired
    private ProtagonistaRepo protagonistaRepository;

    @GetMapping
    public List<Protagonista> getAllProtagonistas() {
        return protagonistaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Protagonista> getProtagonistaById(@PathVariable Long id) {
        Optional<Protagonista> protagonista = protagonistaRepository.findById(id);
        return protagonista.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dificultad/{nivel}")
    public List<Protagonista> getProtagonistasPorDificultad(@PathVariable String nivel) {
        return protagonistaRepository.findByNivelDificultad(nivel);
    }

    @GetMapping("/salud-mayor-que/{salud}")
    public List<Protagonista> getProtagonistasConSaludMayor(@PathVariable Integer salud) {
        return protagonistaRepository.findBySaludGreaterThan(salud);
    }

    @GetMapping("/arma/{arma}")
    public List<Protagonista> getProtagonistasPorArma(@PathVariable String arma) {
        return protagonistaRepository.findByArmaActual(arma);
    }

    @GetMapping("/personaje/{nombrePersonaje}")
    public ResponseEntity<Protagonista> getProtagonistaPorNombrePersonaje(@PathVariable String nombrePersonaje) {
        Optional<Protagonista> protagonista = protagonistaRepository.findByNombrePersonaje(nombrePersonaje);
        return protagonista.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Protagonista createProtagonista(@RequestBody Protagonista protagonista) {
        return protagonistaRepository.save(protagonista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Protagonista> updateProtagonista(@PathVariable Long id, @RequestBody Protagonista protagonistaDetails) {
        Optional<Protagonista> optionalProtagonista = protagonistaRepository.findById(id);
        if (optionalProtagonista.isPresent()) {
            Protagonista protagonista = optionalProtagonista.get();
            protagonista.setSalud(protagonistaDetails.getSalud());
            protagonista.setInventario(protagonistaDetails.getInventario());
            protagonista.setArmaActual(protagonistaDetails.getArmaActual());
            protagonista.setNivelDificultad(protagonistaDetails.getNivelDificultad());

            if (protagonistaDetails.getPersonaje() != null) {
                protagonista.setPersonaje(protagonistaDetails.getPersonaje());
            }

            return ResponseEntity.ok(protagonistaRepository.save(protagonista));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProtagonista(@PathVariable Long id) {
        if (protagonistaRepository.existsById(id)) {
            protagonistaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}