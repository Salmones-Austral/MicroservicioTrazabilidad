package cl.SalmonesAustral.Trazabilidad.client;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CriaderoClient {

    private static final Logger logger = LoggerFactory.getLogger(CriaderoClient.class);
    private final WebClient webClient;

    public CriaderoClient(@Qualifier("criaderosWebClient") WebClient webClient) {
        this.webClient = webClient; 
    }
    public String obtenerNombreCriadero(int id) {
        logger.info("Llamando a microservicio criadero por id: {}", id);
        try {
            Map<String, Object> respuesta = webClient.get()
                    .uri("/{id}/nombre", id)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), 
                            response -> Mono.error(new RuntimeException("Error al consultar criadero")))
                    .bodyToMono(Map.class) // (O Map.class si aplica el punto 1)
                    .block();
                if (respuesta != null && respuesta.containsKey("nombre")) {
                    return respuesta.get("nombre").toString();
                }
                return "Nombre no encontrado";
        } catch (Exception e) {
            logger.error("No se pudo obtener el microservicio criadero por el id {}. Detalle: {}", id, e.getMessage());
            return "criadero Desconocida"; 
        }
    }
}
