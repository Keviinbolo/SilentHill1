package com.Kevin.SilentHill1.Controllers;

import com.Kevin.SilentHill1.Entities.Objeto;
import com.Kevin.SilentHill1.Repository.ObjetoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/SilentHill1/objetos")
@CrossOrigin(origins = "*")
public class ObjetoController {

    @Autowired
    private ObjetoRepo objetoRepository;

    @GetMapping
    public List<Objeto> getAllObjetos() {
        return objetoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Objeto> getObjetoById(@PathVariable Long id) {
        Optional<Objeto> objeto = objetoRepository.findById(id);
        return objeto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Objeto> getObjetoByNombre(@PathVariable String nombre) {
        Optional<Objeto> objeto = objetoRepository.findByNombre(nombre);
        return objeto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public List<Objeto> getObjetosPorTipo(@PathVariable String tipo) {
        return objetoRepository.findByTipo(tipo);
    }

    @GetMapping("/rareza/{rareza}")
    public List<Objeto> getObjetosPorRareza(@PathVariable String rareza) {
        return objetoRepository.findByRareza(rareza);
    }

    @GetMapping("/buscar/{texto}")
    public List<Objeto> buscarObjetosPorNombre(@PathVariable String texto) {
        return objetoRepository.findByNombreContainingIgnoreCase(texto);
    }

    @GetMapping("/ubicacion/{nombreUbicacion}")
    public List<Objeto> getObjetosEnUbicacion(@PathVariable String nombreUbicacion) {
        return objetoRepository.findObjetosEnUbicacion(nombreUbicacion);
    }

    @GetMapping("/filtro")
    public List<Objeto> getObjetosPorTipoYRareza(
            @RequestParam String tipo,
            @RequestParam String rareza) {
        return objetoRepository.findByTipoAndRareza(tipo, rareza);
    }

    @PostMapping
    public Objeto createObjeto(@RequestBody Objeto objeto) {
        return objetoRepository.save(objeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Objeto> updateObjeto(@PathVariable Long id, @RequestBody Objeto objetoDetails) {
        Optional<Objeto> optionalObjeto = objetoRepository.findById(id);
        if (optionalObjeto.isPresent()) {
            Objeto objeto = optionalObjeto.get();
            objeto.setNombre(objetoDetails.getNombre());
            objeto.setTipo(objetoDetails.getTipo());
            objeto.setDescripcion(objetoDetails.getDescripcion());
            objeto.setEfecto(objetoDetails.getEfecto());
            objeto.setRareza(objetoDetails.getRareza());

            // Actualizar relaciones si se proporcionan
            if (objetoDetails.getUbicaciones() != null) {
                objeto.setUbicaciones(objetoDetails.getUbicaciones());
            }

            return ResponseEntity.ok(objetoRepository.save(objeto));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjeto(@PathVariable Long id) {
        if (objetoRepository.existsById(id)) {
            objetoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}