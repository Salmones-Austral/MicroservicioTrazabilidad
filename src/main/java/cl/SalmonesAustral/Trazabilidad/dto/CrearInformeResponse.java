package cl.SalmonesAustral.Trazabilidad.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrearInformeResponse {
    private String nombreCriadero;
    private String nombreJaula;
    private Double porcentajeMortalidad;
    private ExSanitarioDto datosSanitarios;
}
