package com.Kevin.SilentHill1.Controllers;

import com.Kevin.SilentHill1.Entities.Ubicacion;
import com.Kevin.SilentHill1.Repository.UbicacionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/SilentHill1/ubicaciones")
@CrossOrigin(origins = "*")
public class UbicacionController {

    @Autowired
    private UbicacionRepo ubicacionRepository;

    @GetMapping
    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getUbicacionById(@PathVariable Long id) {
        Optional<Ubicacion> ubicacion = ubicacionRepository.findById(id);
        return ubicacion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Ubicacion> getUbicacionByNombre(@PathVariable String nombre) {
        Optional<Ubicacion> ubicacion = ubicacionRepository.findByNombre(nombre);
        return ubicacion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public List<Ubicacion> getUbicacionesPorTipo(@PathVariable String tipo) {
        return ubicacionRepository.findByTipo(tipo);
    }

    @GetMapping("/peligro/{nivel}")
    public List<Ubicacion> getUbicacionesPorPeligro(@PathVariable Integer nivel) {
        return ubicacionRepository.findByPeligroNivelGreaterThanEqual(nivel);
    }

    @GetMapping("/buscar/{texto}")
    public List<Ubicacion> buscarUbicacionesPorNombre(@PathVariable String texto) {
        return ubicacionRepository.findByNombreContainingIgnoreCase(texto);
    }

    @GetMapping("/objeto/{nombreObjeto}")
    public List<Ubicacion> getUbicacionesConObjeto(@PathVariable String nombreObjeto) {
        return ubicacionRepository.findUbicacionesConObjeto(nombreObjeto);
    }

    @PostMapping
    public Ubicacion createUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> updateUbicacion(@PathVariable Long id, @RequestBody Ubicacion ubicacionDetails) {
        Optional<Ubicacion> optionalUbicacion = ubicacionRepository.findById(id);
        if (optionalUbicacion.isPresent()) {
            Ubicacion ubicacion = optionalUbicacion.get();
            ubicacion.setNombre(ubicacionDetails.getNombre());
            ubicacion.setTipo(ubicacionDetails.getTipo());
            ubicacion.setDescripcion(ubicacionDetails.getDescripcion());
            ubicacion.setPeligroNivel(ubicacionDetails.getPeligroNivel());

            // Actualizar relaciones si se proporcionan
            if (ubicacionDetails.getObjetosEncontrados() != null) {
                ubicacion.setObjetosEncontrados(ubicacionDetails.getObjetosEncontrados());
            }

            return ResponseEntity.ok(ubicacionRepository.save(ubicacion));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable Long id) {
        if (ubicacionRepository.existsById(id)) {
            ubicacionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}