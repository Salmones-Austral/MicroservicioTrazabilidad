package cl.SalmonesAustral.Trazabilidad.client;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MortalidadClient {
    //Voy a pedir la variable "porcentaje" de mortalidad
    private static final Logger logger = LoggerFactory.getLogger(MortalidadClient.class);
    private final WebClient webClient;

    public MortalidadClient(@Qualifier("mortalidadesWebClient") WebClient webClient) {
        this.webClient = webClient; 
    }
    public Double obtenerPorcentajeMortalidad(int id) {
        logger.info("Llamando a microservicio mortalidad por id: {}", id);
        try {
            Map<String, Object> jaulaRes = webClient.get()
                    .uri("/{id}/porcentaje", id)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), 
                            response -> Mono.error(new RuntimeException("Error al consultar mortalidad")))
                    .bodyToMono(Map.class) // (O Map.class si aplica el punto 1)
                    .block();
                if (jaulaRes != null && jaulaRes.get("porcentaje") != null) {
                    return Double.valueOf(jaulaRes.get("porcentaje").toString());
                }
                return 0.0;//retorna 0.0 por si no cumple con el if
        } catch (Exception e) {
            logger.error("No se pudo obtener el microservicio mortalidad por el id {}. Detalle: {}", id, e.getMessage());
            return 0.0; //retorna 0.0 por si no cumple con el try
        }
    }
}
