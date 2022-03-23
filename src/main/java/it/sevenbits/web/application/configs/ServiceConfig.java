package it.sevenbits.web.application.configs;

import it.sevenbits.web.application.services.GameService;
import it.sevenbits.web.application.services.IGameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public IGameService gameService() {
        return new GameService();
    }
}
