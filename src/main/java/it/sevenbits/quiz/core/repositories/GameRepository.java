package it.sevenbits.quiz.core.repositories;

import it.sevenbits.quiz.core.model.Game;
import it.sevenbits.quiz.core.repositories.interfaces.IGameRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * implementation of IGameRepository
 */
@Repository
public final class GameRepository implements IGameRepository {
    private static GameRepository gameRepository;
    //private final Game game;
    private final Map<String, Game> gameMap;

    /**
     * constructor
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    private GameRepository() {
        int ten = 2 + 2 + 2 + 2 + 2;
        //game = new Game(ten);
        gameMap = new HashMap<>();
    }

    /**
     * singlton method
     * @return GameRepository
     */
    public static GameRepository getGameRepository() {
        if (gameRepository == null) {
            gameRepository = new GameRepository();
        }
        return gameRepository;
    }

    @Override
    public Game getGame(final String roomId) {
        return gameMap.get(roomId);
    }

    @Override
    public void createGame(final String roomId, final Game game) {
        gameMap.put(roomId, game);
    }

    @Override
    public void updateGameScore(final int score, final String roomId) {
        gameMap.get(roomId).setScore(gameMap.get(roomId).getScore() + score);
    }

    @Override
    public int getGameScore(final String roomId) {
        return gameMap.get(roomId).getScore();
    }

    @Override
    public String getIdOfCurrentQuestion(final String roomId) {
        return gameMap.get(roomId).getCurrentQuestionId();
    }

    @Override
    public String getNextQuestionId(final String roomId) {
        return gameMap.get(roomId).getNextId();
    }

}
