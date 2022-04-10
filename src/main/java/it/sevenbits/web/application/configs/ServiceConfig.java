package it.sevenbits.web.application.configs;

import it.sevenbits.web.application.repositories.IGameRepository;
import it.sevenbits.web.application.repositories.IQuestionRepository;
import it.sevenbits.web.application.services.GameService;
import it.sevenbits.web.application.services.IGameService;
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
     * @param gameRepository - IGameRepository
     * @param questionRepository - IQuestionRepository
     * @return IGameService
     */
    @Bean
    public IGameService gameService(final IGameRepository gameRepository, final IQuestionRepository questionRepository) {
        return new GameService();
    }
}
