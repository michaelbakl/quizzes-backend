package it.sevenbits.web.configs;

import it.sevenbits.quiz.core.services.GameService;
import it.sevenbits.quiz.core.services.IGameService;
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
    public IGameService gameService() {
        return new GameService();
    }
}
