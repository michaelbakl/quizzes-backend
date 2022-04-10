package it.sevenbits.web.application.configs;

import it.sevenbits.web.application.repositories.GameRepository;
import it.sevenbits.web.application.repositories.IGameRepository;
import it.sevenbits.web.application.repositories.IQuestionRepository;
import it.sevenbits.web.application.repositories.MapQuestionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
    bean for IGameRepository
**/
@Configuration
public class RepositoryConfig {
    /**
     * Bean for IGameRepository
     *
     * @return IGameRepository
     */
    @Bean
    public IGameRepository gameRepository() {
        return GameRepository.getGameRepository();
    }

    /**
     * Bean for IQuestionRepository
     *
     * @return IQuestionRepository
     */
    @Bean
    public IQuestionRepository questionRepository() {
        return MapQuestionRepository.getQuestionRepository();
    }
}
