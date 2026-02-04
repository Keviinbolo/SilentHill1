package com.Kevin.SilentHill1.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class UbicacionDTO {
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
    @Min(value = 1, message = "El nivel de peligro mínimo es 1")
    @Max(value = 10, message = "El nivel de peligro máximo es 10")
    private Integer peligroNivel;

    // Constructores
    public UbicacionDTO() {}

    public UbicacionDTO(Long id, String nombre, String tipo, String descripcion, Integer peligroNivel) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.peligroNivel = peligroNivel;
    }


}