package com.Kevin.SilentHill1.Entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enemigos")
public class Enemigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    @Column(nullable = false)
    private String nombre;
    @Getter @Setter
    private String tipo;
    @Getter @Setter
    private String debilidad;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    @JsonIgnoreProperties({"enemigos", "objetosEncontrados"})
    @Getter @Setter
    private Ubicacion ubicacion;

    // Constructores, getters y setters igual...
    public Enemigo() {}

    public Enemigo(String nombre, String tipo, String debilidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.debilidad = debilidad;
    }


}