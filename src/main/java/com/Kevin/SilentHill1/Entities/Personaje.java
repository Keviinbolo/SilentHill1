package com.Kevin.SilentHill1.Entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "personajes")
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String historia;

    @OneToOne(mappedBy = "personaje", cascade = CascadeType.ALL)
    @JsonIgnore // Para evitar referencias circulares en la serialización JSON
    private Protagonista protagonista;

    // Constructores, getters y setters igual...
    public Personaje() {}

    public Personaje(String nombre, String historia) {
        this.nombre = nombre;
        this.historia = historia;
    }

    // Getters y Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getHistoria() { return historia; }
    public void setHistoria(String historia) { this.historia = historia; }

    public Protagonista getProtagonista() { return protagonista; }
    public void setProtagonista(Protagonista protagonista) { this.protagonista = protagonista; }
}