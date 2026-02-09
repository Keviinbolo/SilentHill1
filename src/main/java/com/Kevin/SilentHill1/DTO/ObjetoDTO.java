package com.Kevin.SilentHill1.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

public class ObjetoDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;
    @Getter @Setter
    @Size(max = 50, message = "El tipo no puede exceder los 50 caracteres")
    private String tipo;
    @Getter @Setter
    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;
    @Getter @Setter
    @Size(max = 200, message = "El efecto no puede exceder los 200 caracteres")
    private String efecto;
    @Getter @Setter
    @Pattern(regexp = "^(Común|Raro|Épico|Legendario)$",
            message = "La rareza debe ser: Común, Raro, Épico o Legendario")
    private String rareza;

    // Constructores
    public ObjetoDTO() {}

    public ObjetoDTO(Long id, String nombre, String tipo, String descripcion, String efecto, String rareza) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.efecto = efecto;
        this.rareza = rareza;
    }


}