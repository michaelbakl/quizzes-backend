package it.sevenbits.quiz.web.config;

import it.sevenbits.quiz.core.repositories.game.PostgresGameRepository;
import it.sevenbits.quiz.core.repositories.question.PostgresQuestionRepository;
import it.sevenbits.quiz.core.repositories.room.PostgresRoomRepository;
import it.sevenbits.quiz.core.repositories.game.IGameRepository;
import it.sevenbits.quiz.core.repositories.question.IQuestionRepository;
import it.sevenbits.quiz.core.repositories.room.IRoomRepository;
import it.sevenbits.quiz.core.repositories.user.IUserRepository;
import it.sevenbits.quiz.core.repositories.user.PostgresUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;

/**
 * configuration class for repositories
 */
@Configuration
public class RepositoryConfig {

  /**
   * configuration bean for IGameRepository
   * @param jdbcOperations - jdbc
   * @return IGameRepository - implementation of this interface
   */
  @Bean
  public IGameRepository gameRepository(final JdbcOperations jdbcOperations) {
    return new PostgresGameRepository(jdbcOperations);
  }

  /**
   * configuration bean for IQuestionRepository
   * @param jdbcOperations - jdbc
   * @return IQuestionRepository - implementation of this interface
   */
  @Bean
  public IQuestionRepository questionRepository(final JdbcOperations jdbcOperations) {
    return new PostgresQuestionRepository(jdbcOperations);
  }

  /**
   * configuration bean for IRoomRepository
   * @param jdbcOperations - jdbc
   * @return IRoomRepository - implementation of this interface
   */
  @Bean
  public IRoomRepository roomRepository(final JdbcOperations jdbcOperations) {
    return new PostgresRoomRepository(jdbcOperations);
  }


  /**
   * configuration bean for IUserRepository
   * @param jdbcOperations - jdbc
   * @return IUserRepository - implementation of this interface
   */
  @Bean
  public IUserRepository userRepository(final JdbcOperations jdbcOperations) {
    return new PostgresUserRepository(jdbcOperations);
  }

}
