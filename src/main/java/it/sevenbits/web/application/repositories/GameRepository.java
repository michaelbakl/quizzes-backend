package it.sevenbits.web.application.repositories;

import it.sevenbits.web.application.model.Game;
import it.sevenbits.web.application.model.Question;

/**
 * implementation of IGameRepository
 */
public final class GameRepository implements IGameRepository {
    private static GameRepository gameRepository;
    private final Game game;

    /**
     * constructor
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    private GameRepository() {
        int ten = 10;
        game = new Game(ten);
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
    public Game getGame() {
        return game;
    }

    @Override
    public void updateGameScore(final int score) {
        game.setScore(game.getScore() + score);
    }

    @Override
    public int getGameScore() {
        return game.getScore();
    }

    @Override
    public String getIdOfCurrentQuestion() {
        return game.getCurrentQuestionId();
    }

    @Override
    public String getNextQuestionId() {
        return game.getNextId();
    }

}
