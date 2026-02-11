package com.Kevin.SilentHill1.Controllers;

import com.Kevin.SilentHill1.DTO.ObjetoDTO;
import com.Kevin.SilentHill1.Service.ObjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetos")
@CrossOrigin(origins = "*")
public class ObjetoController {

    @Autowired
    private ObjetoService objetoService;

    @GetMapping
    public ResponseEntity<List<ObjetoDTO>> getAllObjetos() {
        List<ObjetoDTO> objetos = objetoService.getAllObjetos();
        return ResponseEntity.ok(objetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoDTO> getObjetoById(@PathVariable Long id) {
        ObjetoDTO objeto = objetoService.getObjetoById(id);
        return ResponseEntity.ok(objeto);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ObjetoDTO> getObjetoByNombre(@PathVariable String nombre) {
        ObjetoDTO objeto = objetoService.getObjetoByNombre(nombre);
        return ResponseEntity.ok(objeto);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ObjetoDTO>> getObjetosPorTipo(@PathVariable String tipo) {
        List<ObjetoDTO> objetos = objetoService.getObjetosPorTipo(tipo);
        return ResponseEntity.ok(objetos);
    }

    @GetMapping("/rareza/{rareza}")
    public ResponseEntity<List<ObjetoDTO>> getObjetosPorRareza(@PathVariable String rareza) {
        List<ObjetoDTO> objetos = objetoService.getObjetosPorRareza(rareza);
        return ResponseEntity.ok(objetos);
    }

    @GetMapping("/buscar/{texto}")
    public ResponseEntity<List<ObjetoDTO>> buscarObjetos(@PathVariable String texto) {
        List<ObjetoDTO> objetos = objetoService.buscarObjetos(texto);
        return ResponseEntity.ok(objetos);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<ObjetoDTO>> getObjetosPorTipoYRareza(
            @RequestParam String tipo,
            @RequestParam String rareza) {
        // Primero filtramos por tipo
        List<ObjetoDTO> objetosTipo = objetoService.getObjetosPorTipo(tipo);
        // Luego filtramos por rareza (manualmente ya que no hay método combinado)
        List<ObjetoDTO> objetosFiltrados = objetosTipo.stream()
                .filter(objeto -> objeto.getRareza().equalsIgnoreCase(rareza))
                .toList();
        return ResponseEntity.ok(objetosFiltrados);
    }

    @PostMapping
    public ResponseEntity<ObjetoDTO> createObjeto(@Valid @RequestBody ObjetoDTO objetoDTO) {
        ObjetoDTO created = objetoService.createObjeto(objetoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetoDTO> updateObjeto(
            @PathVariable Long id,
            @Valid @RequestBody ObjetoDTO objetoDTO) {
        ObjetoDTO updated = objetoService.updateObjeto(id, objetoDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjeto(@PathVariable Long id) {
        objetoService.deleteObjeto(id);
        return ResponseEntity.noContent().build();
    }
}