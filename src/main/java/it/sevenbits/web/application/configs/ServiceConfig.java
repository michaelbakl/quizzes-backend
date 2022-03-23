package it.sevenbits.web.application.configs;

import it.sevenbits.web.application.repositories.IGameRepository;
import it.sevenbits.web.application.repositories.IQuestionRepository;
import it.sevenbits.web.application.services.GameService;
import it.sevenbits.web.application.services.IGameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public IGameService gameService(IGameRepository gameRepository, IQuestionRepository questionRepository) {
        return new GameService(gameRepository, questionRepository);
    }
}
