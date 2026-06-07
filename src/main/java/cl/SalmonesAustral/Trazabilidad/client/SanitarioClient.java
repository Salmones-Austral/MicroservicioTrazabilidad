package cl.SalmonesAustral.Trazabilidad.client;

import cl.SalmonesAustral.Trazabilidad.dto.ExSanitarioDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;;

@Component
public class SanitarioClient {
    //voy a usar un dto por que necesito la mayoria de valores de sanitario
    private static final Logger logger = LoggerFactory.getLogger(SanitarioClient.class);
    private final WebClient webClient;

    public SanitarioClient(@Qualifier("sanitariosWebClient") WebClient webClient) {
        this.webClient = webClient; 
    }
    public ExSanitarioDto obtenerDatosSanitario(int id) {
    logger.info("Llamando a microservicio sanitario para obtener todos los datos por id: {}", id);
    try {
        ExSanitarioDto sanitarioRes = webClient.get()
                .uri("/{id}", id) // <-- Asegúrate de usar la URI correcta del otro microservicio
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), 
                        response -> Mono.error(new RuntimeException("Error al consultar los datos completos")))
                .bodyToMono(ExSanitarioDto.class)
                .block();
        if (sanitarioRes == null) {
            return new ExSanitarioDto(); 
        }
        return sanitarioRes;
        } catch (Exception e) {
            logger.error("No se pudieron obtener los datos por el id {}. Detalle: {}", id, e.getMessage());
            return new ExSanitarioDto(); // Retorna un objeto vacío en caso de error
        }
    }

}