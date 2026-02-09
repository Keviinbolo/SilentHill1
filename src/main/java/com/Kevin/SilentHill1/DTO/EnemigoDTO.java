package com.Kevin.SilentHill1.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

public class EnemigoDTO {
    @Setter @Getter
    private Long id;
    @Setter @Getter
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
    @Setter @Getter
    @Size(max = 50, message = "El tipo no puede exceder los 50 caracteres")
    private String tipo;
    @Setter @Getter
    @Size(max = 100, message = "La debilidad no puede exceder los 100 caracteres")
    private String debilidad;
    @Setter @Getter
    private Long ubicacionId; // Solo el ID de la ubicación

    // Constructores
    public EnemigoDTO() {}

    public EnemigoDTO(Long id, String nombre, String tipo, String debilidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.debilidad = debilidad;
    }

}