package com.Kevin.SilentHill1.Entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "objetos")
public class Objeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String tipo;
    private String descripcion;
    private String efecto;
    private String rareza;

    @ManyToMany(mappedBy = "objetosEncontrados")
    @JsonIgnore // ← AÑADIR ESTO
    private Set<Ubicacion> ubicaciones = new HashSet<>();

    // Constructores, getters y setters igual...
    public Objeto() {}

    public Objeto(String nombre, String tipo, String descripcion, String efecto, String rareza) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.efecto = efecto;
        this.rareza = rareza;
    }

    // Getters y Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEfecto() { return efecto; }
    public void setEfecto(String efecto) { this.efecto = efecto; }

    public String getRareza() { return rareza; }
    public void setRareza(String rareza) { this.rareza = rareza; }

    public Set<Ubicacion> getUbicaciones() { return ubicaciones; }
    public void setUbicaciones(Set<Ubicacion> ubicaciones) { this.ubicaciones = ubicaciones; }
}