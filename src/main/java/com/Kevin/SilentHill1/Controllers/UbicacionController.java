package com.Kevin.SilentHill1.Controllers;

import com.Kevin.SilentHill1.DTO.UbicacionDTO;
import com.Kevin.SilentHill1.Service.UbicacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@CrossOrigin(origins = "*")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public ResponseEntity<List<UbicacionDTO>> getAllUbicaciones() {
        List<UbicacionDTO> ubicaciones = ubicacionService.getAllUbicaciones();
        return ResponseEntity.ok(ubicaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> getUbicacionById(@PathVariable Long id) {
        UbicacionDTO ubicacion = ubicacionService.getUbicacionById(id);
        return ResponseEntity.ok(ubicacion);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<UbicacionDTO> getUbicacionByNombre(@PathVariable String nombre) {
        UbicacionDTO ubicacion = ubicacionService.getUbicacionByNombre(nombre);
        return ResponseEntity.ok(ubicacion);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<UbicacionDTO>> getUbicacionesPorTipo(@PathVariable String tipo) {
        List<UbicacionDTO> ubicaciones = ubicacionService.getUbicacionesPorTipo(tipo);
        return ResponseEntity.ok(ubicaciones);
    }

    @GetMapping("/peligro/{nivel}")
    public ResponseEntity<List<UbicacionDTO>> getUbicacionesPorPeligro(@PathVariable Integer nivel) {
        List<UbicacionDTO> ubicaciones = ubicacionService.getUbicacionesPorPeligro(nivel);
        return ResponseEntity.ok(ubicaciones);
    }

    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<UbicacionDTO>> buscarUbicaciones(@PathVariable String texto) {
        List<UbicacionDTO> ubicaciones = ubicacionService.buscarUbicaciones(texto);
        return ResponseEntity.ok(ubicaciones);
    }

    @PostMapping
    public ResponseEntity<UbicacionDTO> createUbicacion(@Valid @RequestBody UbicacionDTO ubicacionDTO) {
        UbicacionDTO created = ubicacionService.createUbicacion(ubicacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> updateUbicacion(
            @PathVariable Long id,
            @Valid @RequestBody UbicacionDTO ubicacionDTO) {
        UbicacionDTO updated = ubicacionService.updateUbicacion(id, ubicacionDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable Long id) {
        ubicacionService.deleteUbicacion(id);
        return ResponseEntity.noContent().build();
    }
}