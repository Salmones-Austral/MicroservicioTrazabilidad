package cl.SalmonesAustral.Trazabilidad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateTrazabilidadRequest(

    @Positive(message = "El id de la jaula debe ser un número positivo")
    int jaulaId,

    @NotBlank(message = "El tipo de evento no puede estar vacio")
    String tipoEvento,

    @NotBlank(message = "La descripción no puede estar vacia") 
    String descripcion,

    @NotBlank(message = "El origen no puede estar vacio")
    String origen,

    @Positive(message = "El id de referencia debe ser un número positivo")
    int referenciaId,

    @NotBlank(message = "La fecha no puede estar vacia")
    String fecha
) {}