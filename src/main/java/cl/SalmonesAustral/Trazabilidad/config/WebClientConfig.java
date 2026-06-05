package cl.SalmonesAustral.Trazabilidad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    //si no esta este, no funciona nada en webclient.
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
    /* 
    @Bean
    public WebClient webClientJaula(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8081/api/v1/jaula").build();
    }

    @Bean
    public WebClient webClientSanidad(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8082/api/v1/sanidad").build();
    }

    @Bean
    public WebClient webClientMonitoreo(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8083/api/v1/monitoreo").build();
    }

    @Bean
    public WebClient webClientTratamiento(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8084/api/v1/tratamiento").build();
    }

    @Bean
    public WebClient webClientCosecha(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8085/api/v1/cosecha").build();
    }
    */
}
