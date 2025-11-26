package com.Kevin.SilentHill1.Entities;


import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ubicaciones")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String tipo;
    private String descripcion;
    private Integer peligroNivel;

    @ManyToMany
    @JoinTable(
            name = "ubicacion_objeto",
            joinColumns = @JoinColumn(name = "ubicacion_id"),
            inverseJoinColumns = @JoinColumn(name = "objeto_id")
    )
    private Set<Objeto> objetosEncontrados = new HashSet<>();

    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL)
    private Set<Enemigo> enemigos = new HashSet<>();

    // Constructores
    public Ubicacion() {}

    public Ubicacion(String nombre, String tipo, String descripcion, Integer peligroNivel) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.peligroNivel = peligroNivel;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getPeligroNivel() { return peligroNivel; }
    public void setPeligroNivel(Integer peligroNivel) { this.peligroNivel = peligroNivel; }

    public Set<Objeto> getObjetosEncontrados() { return objetosEncontrados; }
    public void setObjetosEncontrados(Set<Objeto> objetosEncontrados) { this.objetosEncontrados = objetosEncontrados; }

    public Set<Enemigo> getEnemigos() { return enemigos; }
    public void setEnemigos(Set<Enemigo> enemigos) { this.enemigos = enemigos; }
}