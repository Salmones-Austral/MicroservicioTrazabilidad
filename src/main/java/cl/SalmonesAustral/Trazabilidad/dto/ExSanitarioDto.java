package cl.SalmonesAustral.Trazabilidad.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class ExSanitarioDto {

    private Integer id;
    private String enfermedad;
    private String medicamento;
    private Double dosis;
    private Integer duracionDias;
    private Integer diasResguardo;
    private LocalDate fechaInicio;
    private String estado;
    private Boolean bloqueaCosecha;
    private String observaciones;
}
