package it.sevenbits.web.configs;

import it.sevenbits.quiz.core.repositories.GameRepository;
import it.sevenbits.quiz.core.repositories.MapQuestionRepository;
import it.sevenbits.quiz.core.repositories.RoomRepository;
import it.sevenbits.quiz.core.repositories.interfaces.IGameRepository;
import it.sevenbits.quiz.core.repositories.interfaces.IQuestionRepository;
import it.sevenbits.quiz.core.repositories.interfaces.IRoomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * configuration class for repositories
 */
@Configuration
public class RepositoryConfig {

  /**
   * configuration bean for IGameRepository
   * @return IGameRepository - implementation of this interface
   */
  @Bean
  public IGameRepository gameRepository() {
    return new GameRepository();
  }

  /**
   * configuration bean for IQuestionRepository
   * @return IQuestionRepository - implementation of this interface
   */
  @Bean
  public IQuestionRepository questionRepository() {
    return new MapQuestionRepository();
  }

  /**
   * configuration bean for IRoomRepository
   * @return IRoomRepository - implementation of this interface
   */
  @Bean
  public IRoomRepository roomRepository() {
    return new RoomRepository();
  }

}
