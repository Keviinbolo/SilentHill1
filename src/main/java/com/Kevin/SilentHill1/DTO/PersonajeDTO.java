package com.Kevin.SilentHill1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class PersonajeDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
    @Getter @Setter
    @Size(max = 2000, message = "La historia no puede exceder los 2000 caracteres")
    private String historia;

    // Constructores
    public PersonajeDTO() {}

    public PersonajeDTO(Long id, String nombre, String historia) {
        this.id = id;
        this.nombre = nombre;
        this.historia = historia;
    }


}