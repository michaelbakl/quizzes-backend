package it.sevenbits.quiz.web.config;

import it.sevenbits.quiz.core.repositories.game.IGameRepository;
import it.sevenbits.quiz.core.repositories.question.IQuestionRepository;
import it.sevenbits.quiz.core.repositories.room.IRoomRepository;
import it.sevenbits.quiz.core.services.GameService;
import it.sevenbits.quiz.core.services.RoomService;
import it.sevenbits.quiz.core.services.interfaces.IGameService;
import it.sevenbits.quiz.core.services.interfaces.IRoomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * service config
 */
@Configuration
public class ServiceConfig {
    /**
     * bean for IGameService
     * @param gameRepository - repo for games
     * @param questionRepository - repo for questions
     * @param roomRepository - repo for rooms
     * @return implementation of interface IGameService
     */
    @Bean
    public IGameService gameService(final IGameRepository gameRepository,
                                    final IQuestionRepository questionRepository,
                                    final IRoomRepository roomRepository) {
        return new GameService(gameRepository, questionRepository, roomRepository);
    }

    /**
     * bean for IRoomService
     * @param roomRepository - room repo
     * @return implementation of interface IRoomService
     */
    @Bean
    public IRoomService roomService(final IRoomRepository roomRepository) {
        return new RoomService(roomRepository);
    }
}
