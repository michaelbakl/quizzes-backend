package it.sevenbits.web.application.configs;

import it.sevenbits.web.application.repositories.GameRepository;
import it.sevenbits.web.application.repositories.IGameRepository;
import it.sevenbits.web.application.repositories.IQuestionRepository;
import it.sevenbits.web.application.repositories.MapQuestionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {
    @Bean
    public IGameRepository gameRepository() {
        return new GameRepository();
    }

    @Bean
    public IQuestionRepository questionRepository() {
        return new MapQuestionRepository();
    }
}
