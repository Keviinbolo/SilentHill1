package com.Kevin.SilentHill1.Controllers;

import com.Kevin.SilentHill1.DTO.EnemigoDTO;
import com.Kevin.SilentHill1.Service.EnemigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enemigos")
@CrossOrigin(origins = "*")
public class EnemigoController {

    @Autowired
    private EnemigoService enemigoService;

    @GetMapping
    public ResponseEntity<List<EnemigoDTO>> getAllEnemigos() {
        List<EnemigoDTO> enemigos = enemigoService.getAllEnemigos();
        return ResponseEntity.ok(enemigos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnemigoDTO> getEnemigoById(@PathVariable Long id) {
        EnemigoDTO enemigo = enemigoService.getEnemigoById(id);
        return ResponseEntity.ok(enemigo);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EnemigoDTO> getEnemigoByNombre(@PathVariable String nombre) {
        EnemigoDTO enemigo = enemigoService.getEnemigoByNombre(nombre);
        return ResponseEntity.ok(enemigo);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<EnemigoDTO>> getEnemigosPorTipo(@PathVariable String tipo) {
        List<EnemigoDTO> enemigos = enemigoService.getEnemigosPorTipo(tipo);
        return ResponseEntity.ok(enemigos);
    }

    @GetMapping("/debilidad/{debilidad}")
    public ResponseEntity<List<EnemigoDTO>> getEnemigosPorDebilidad(@PathVariable String debilidad) {
        List<EnemigoDTO> enemigos = enemigoService.getEnemigosPorDebilidad(debilidad);
        return ResponseEntity.ok(enemigos);
    }

    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<EnemigoDTO>> buscarEnemigos(@PathVariable String texto) {
        List<EnemigoDTO> enemigos = enemigoService.buscarEnemigos(texto);
        return ResponseEntity.ok(enemigos);
    }

    @GetMapping("/ubicacion/{ubicacionId}")
    public ResponseEntity<List<EnemigoDTO>> getEnemigosEnUbicacion(@PathVariable Long ubicacionId) {
        // Filtramos manualmente ya que no hay método directo
        List<EnemigoDTO> allEnemigos = enemigoService.getAllEnemigos();
        List<EnemigoDTO> enemigosEnUbicacion = allEnemigos.stream()
                .filter(enemigo -> enemigo.getUbicacionId() != null &&
                        enemigo.getUbicacionId().equals(ubicacionId))
                .toList();
        return ResponseEntity.ok(enemigosEnUbicacion);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<EnemigoDTO>> getEnemigosPorTipoYUbicacion(
            @RequestParam String tipo,
            @RequestParam Long ubicacionId) {
        // Filtramos manualmente
        List<EnemigoDTO> enemigosTipo = enemigoService.getEnemigosPorTipo(tipo);
        List<EnemigoDTO> enemigosFiltrados = enemigosTipo.stream()
                .filter(enemigo -> enemigo.getUbicacionId() != null &&
                        enemigo.getUbicacionId().equals(ubicacionId))
                .toList();
        return ResponseEntity.ok(enemigosFiltrados);
    }

    @PostMapping
    public ResponseEntity<EnemigoDTO> createEnemigo(@Valid @RequestBody EnemigoDTO enemigoDTO) {
        EnemigoDTO created = enemigoService.createEnemigo(enemigoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnemigoDTO> updateEnemigo(
            @PathVariable Long id,
            @Valid @RequestBody EnemigoDTO enemigoDTO) {
        EnemigoDTO updated = enemigoService.updateEnemigo(id, enemigoDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnemigo(@PathVariable Long id) {
        enemigoService.deleteEnemigo(id);
        return ResponseEntity.noContent().build();
    }
}