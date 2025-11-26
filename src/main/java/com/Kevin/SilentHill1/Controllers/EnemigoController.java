package com.Kevin.SilentHill1.Controllers;


import com.Kevin.SilentHill1.Entities.Enemigo;
import com.Kevin.SilentHill1.Repository.EnemigoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enemigos")
@CrossOrigin(origins = "*")
public class EnemigoController {

    @Autowired
    private EnemigoRepo enemigoRepository;

    @GetMapping
    public List<Enemigo> getAllEnemigos() {
        return enemigoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enemigo> getEnemigoById(@PathVariable Long id) {
        Optional<Enemigo> enemigo = enemigoRepository.findById(id);
        return enemigo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Enemigo> getEnemigoByNombre(@PathVariable String nombre) {
        Optional<Enemigo> enemigo = enemigoRepository.findByNombre(nombre);
        return enemigo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public List<Enemigo> getEnemigosPorTipo(@PathVariable String tipo) {
        return enemigoRepository.findByTipo(tipo);
    }

    @GetMapping("/debilidad/{debilidad}")
    public List<Enemigo> getEnemigosPorDebilidad(@PathVariable String debilidad) {
        return enemigoRepository.findByDebilidad(debilidad);
    }

    @GetMapping("/buscar/{texto}")
    public List<Enemigo> buscarEnemigosPorNombre(@PathVariable String texto) {
        return enemigoRepository.findByNombreContainingIgnoreCase(texto);
    }

    @GetMapping("/ubicacion/{nombreUbicacion}")
    public List<Enemigo> getEnemigosEnUbicacion(@PathVariable String nombreUbicacion) {
        return enemigoRepository.findEnemigosEnUbicacion(nombreUbicacion);
    }

    @GetMapping("/filtro")
    public List<Enemigo> getEnemigosPorTipoYUbicacion(
            @RequestParam String tipo,
            @RequestParam Long ubicacionId) {
        return enemigoRepository.findByTipoAndUbicacionId(tipo, ubicacionId);
    }

    @PostMapping
    public Enemigo createEnemigo(@RequestBody Enemigo enemigo) {
        return enemigoRepository.save(enemigo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enemigo> updateEnemigo(@PathVariable Long id, @RequestBody Enemigo enemigoDetails) {
        Optional<Enemigo> optionalEnemigo = enemigoRepository.findById(id);
        if (optionalEnemigo.isPresent()) {
            Enemigo enemigo = optionalEnemigo.get();
            enemigo.setNombre(enemigoDetails.getNombre());
            enemigo.setTipo(enemigoDetails.getTipo());
            enemigo.setDebilidad(enemigoDetails.getDebilidad());

            // Actualizar ubicación si se proporciona
            if (enemigoDetails.getUbicacion() != null) {
                enemigo.setUbicacion(enemigoDetails.getUbicacion());
            }

            return ResponseEntity.ok(enemigoRepository.save(enemigo));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnemigo(@PathVariable Long id) {
        if (enemigoRepository.existsById(id)) {
            enemigoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
