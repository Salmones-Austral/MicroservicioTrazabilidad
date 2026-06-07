package cl.SalmonesAustral.Trazabilidad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearInformeRequest {
    private int idCriadero;
    private int idJaula;
    private int idMortalidad;
    private int idSanitario;
}