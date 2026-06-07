package cl.SalmonesAustral.Trazabilidad.client;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class JaulaClient {

    private static final Logger logger = LoggerFactory.getLogger(JaulaClient.class);
    private final WebClient webClient;

    public JaulaClient(@Qualifier("jaulasWebClient") WebClient webClient) {
        this.webClient = webClient; 
    }
    public String obtenerNombreJaula(int id) {
        logger.info("Llamando a microservicio jaula por id: {}", id);
        try {
            Map<String, Object> jaulaRes = webClient.get()
                    .uri("/{id}/nombre", id)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), 
                            response -> Mono.error(new RuntimeException("Error al consultar jaula")))
                    .bodyToMono(Map.class) // (O Map.class si aplica el punto 1)
                    .block();
                if (jaulaRes != null && jaulaRes.containsKey("nombre")) {
                    return jaulaRes.get("nombre").toString();
                }
                return "Nombre no encontrado";
        } catch (Exception e) {
            logger.error("No se pudo obtener el microservicio jaula por el id {}. Detalle: {}", id, e.getMessage());
            return "Jaula Desconocida"; 
        }
    }
}
