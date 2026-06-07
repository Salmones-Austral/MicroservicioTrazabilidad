package cl.SalmonesAustral.Trazabilidad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    //si no esta este, no funciona nada en webclient.
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
    @Bean
    public WebClient criaderosWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8080/api/v1/criaderos").build();
    }
    @Bean
    public WebClient jaulasWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8081/api/v1/jaulas").build();
    }
    @Bean
    public WebClient mortalidadesWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8082/api/v1/mortalidades").build();
    }
    @Bean
    public WebClient sanitariosWebClient(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8087/api/v1/sanitarios").build();
    }
}