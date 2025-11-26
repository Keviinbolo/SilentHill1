package com.Kevin.SilentHill1.Entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "enemigos")
public class Enemigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String tipo;
    private String debilidad;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    @JsonIgnoreProperties({"enemigos", "objetosEncontrados"}) // ← AÑADIR ESTO
    private Ubicacion ubicacion;

    // Constructores, getters y setters igual...
    public Enemigo() {}

    public Enemigo(String nombre, String tipo, String debilidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.debilidad = debilidad;
    }

    // Getters y Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDebilidad() { return debilidad; }
    public void setDebilidad(String debilidad) { this.debilidad = debilidad; }

    public Ubicacion getUbicacion() { return ubicacion; }
    public void setUbicacion(Ubicacion ubicacion) { this.ubicacion = ubicacion; }
}