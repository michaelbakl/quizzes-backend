package it.sevenbits.web.configs;

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
     * Bean for IGameService
     *
     * @return IGameService
     */
    @Bean
    public IGameService gameService() {
        return new GameService();
    }

    /**
     * Bean for IRoomServic e
     * @return IRoomService
     */
    @Bean
    public IRoomService roomService() {
        return new RoomService();
    }
}
