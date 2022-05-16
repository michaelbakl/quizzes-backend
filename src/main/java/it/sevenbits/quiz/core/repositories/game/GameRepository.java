package it.sevenbits.quiz.core.repositories.game;

import it.sevenbits.quiz.core.model.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * implementation of IGameRepository
 */
@Repository
public class GameRepository implements IGameRepository {
    private final Map<String, Game> gameMap;

    /**
     * constructor
     */
    public GameRepository() {
        gameMap = new HashMap<>();
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

    @Override
    public void updateGame(final String roomId, final Game game) {

    }

    @Override
    public boolean checkGameIsInProgress(final String roomId) {
        return false;
    }

}
