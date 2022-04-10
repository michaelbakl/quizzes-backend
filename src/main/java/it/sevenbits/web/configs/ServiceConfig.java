package it.sevenbits.web.configs;

import it.sevenbits.quiz.core.services.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * service config
 */
@Configuration
public class ServiceConfig {
    /**
     * Bean for IGameService
     *
     * @return IGameService
     */
    @Bean
    public GameService gameService() {
        return new GameService();
    }
}
