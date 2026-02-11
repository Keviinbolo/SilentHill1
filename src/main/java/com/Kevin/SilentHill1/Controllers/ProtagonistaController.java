package com.Kevin.SilentHill1.Controllers;

import com.Kevin.SilentHill1.DTO.ProtagonistaDTO;
import com.Kevin.SilentHill1.Service.ProtagonistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/protagonistas")
@CrossOrigin(origins = "*")
public class ProtagonistaController {

    @Autowired
    private ProtagonistaService protagonistaService;

    @GetMapping
    public ResponseEntity<List<ProtagonistaDTO>> getAllProtagonistas() {
        List<ProtagonistaDTO> protagonistas = protagonistaService.getAllProtagonistas();
        return ResponseEntity.ok(protagonistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProtagonistaDTO> getProtagonistaById(@PathVariable Long id) {
        ProtagonistaDTO protagonista = protagonistaService.getProtagonistaById(id);
        return ResponseEntity.ok(protagonista);
    }

    @GetMapping("/dificultad/{nivel}")
    public ResponseEntity<List<ProtagonistaDTO>> getProtagonistasPorDificultad(@PathVariable String nivel) {
        List<ProtagonistaDTO> protagonistas = protagonistaService.getProtagonistasPorDificultad(nivel);
        return ResponseEntity.ok(protagonistas);
    }

    @GetMapping("/salud-mayor-que/{salud}")
    public ResponseEntity<List<ProtagonistaDTO>> getProtagonistasConSaludMayor(@PathVariable Integer salud) {
        List<ProtagonistaDTO> protagonistas = protagonistaService.getProtagonistasConSaludMayor(salud);
        return ResponseEntity.ok(protagonistas);
    }

    @GetMapping("/arma/{arma}")
    public ResponseEntity<List<ProtagonistaDTO>> getProtagonistasPorArma(@PathVariable String arma) {
        List<ProtagonistaDTO> protagonistas = protagonistaService.getProtagonistasPorArma(arma);
        return ResponseEntity.ok(protagonistas);
    }

    @GetMapping("/personaje/{personajeId}")
    public ResponseEntity<ProtagonistaDTO> getProtagonistaPorPersonaje(@PathVariable Long personajeId) {
        // Filtramos manualmente
        List<ProtagonistaDTO> allProtagonistas = protagonistaService.getAllProtagonistas();
        ProtagonistaDTO protagonista = allProtagonistas.stream()
                .filter(p -> p.getPersonajeId() != null && p.getPersonajeId().equals(personajeId))
                .findFirst()
                .orElse(null);

        if (protagonista == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(protagonista);
    }

    @PostMapping
    public ResponseEntity<ProtagonistaDTO> createProtagonista(@Valid @RequestBody ProtagonistaDTO protagonistaDTO) {
        ProtagonistaDTO created = protagonistaService.createProtagonista(protagonistaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProtagonistaDTO> updateProtagonista(
            @PathVariable Long id,
            @Valid @RequestBody ProtagonistaDTO protagonistaDTO) {
        ProtagonistaDTO updated = protagonistaService.updateProtagonista(id, protagonistaDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProtagonista(@PathVariable Long id) {
        protagonistaService.deleteProtagonista(id);
        return ResponseEntity.noContent().build();
    }
}