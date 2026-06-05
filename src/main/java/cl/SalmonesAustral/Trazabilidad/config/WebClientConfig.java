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
    
}
