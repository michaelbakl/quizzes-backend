package it.sevenbits.web.application.repositories;

import it.sevenbits.web.application.model.Game;

/**
 * implementation of IGameRepository
 */
public class GameRepository implements IGameRepository {
    private final Game game;

    /**
     * constructor
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public GameRepository() {
        int ten = 10;
        game = new Game(ten);
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
