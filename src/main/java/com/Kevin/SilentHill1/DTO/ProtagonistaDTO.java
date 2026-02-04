package com.Kevin.SilentHill1.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

public class ProtagonistaDTO {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Min(value = 0, message = "La salud no puede ser menor que 0")
    @Max(value = 200, message = "La salud no puede ser mayor que 200")
    private Integer salud;

    @Getter
    @Setter
    @Size(max = 500, message = "El inventario no puede exceder los 500 caracteres")
    private String inventario;

    @Getter
    @Setter
    @Size(max = 100, message = "El arma actual no puede exceder los 100 caracteres")
    private String armaActual;

    @Getter
    @Setter
    @Pattern(regexp = "^(Fácil|Normal|Difícil|Experto)$",
            message = "La dificultad debe ser: Fácil, Normal, Difícil o Experto")
    private String nivelDificultad;

    @Getter
    @Setter
    @NotNull(message = "El personaje es obligatorio")
    private Long personajeId; // Solo el ID del personaje

    // Constructores
    public ProtagonistaDTO() {
    }

    public ProtagonistaDTO(Long id, Integer salud, String inventario, String armaActual, String nivelDificultad) {
        this.id = id;
        this.salud = salud;
        this.inventario = inventario;
        this.armaActual = armaActual;
        this.nivelDificultad = nivelDificultad;
    }

}