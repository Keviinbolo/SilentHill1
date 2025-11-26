package com.Kevin.SilentHill1.Entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "protagonistas")
public class Protagonista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer salud;
    private String inventario;
    private String armaActual;
    private String nivelDificultad;

    @OneToOne
    @JoinColumn(name = "personaje_id", unique = true)
    @JsonIgnoreProperties({"protagonista"}) // ← AÑADIR ESTO
    private Personaje personaje;

    // Constructores, getters y setters igual...
    public Protagonista() {}

    public Protagonista(Integer salud, String inventario, String armaActual, String nivelDificultad) {
        this.salud = salud;
        this.inventario = inventario;
        this.armaActual = armaActual;
        this.nivelDificultad = nivelDificultad;
    }

    // Getters y Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getSalud() { return salud; }
    public void setSalud(Integer salud) { this.salud = salud; }

    public String getInventario() { return inventario; }
    public void setInventario(String inventario) { this.inventario = inventario; }

    public String getArmaActual() { return armaActual; }
    public void setArmaActual(String armaActual) { this.armaActual = armaActual; }

    public String getNivelDificultad() { return nivelDificultad; }
    public void setNivelDificultad(String nivelDificultad) { this.nivelDificultad = nivelDificultad; }

    public Personaje getPersonaje() { return personaje; }
    public void setPersonaje(Personaje personaje) { this.personaje = personaje; }
}