package it.sevenbits.web.application.repositories;

import it.sevenbits.web.application.model.Game;

public class GameRepository implements IGameRepository {
    private static final GameRepository INSTANCE = new GameRepository();
    private Game game;

    private GameRepository() {
        game = new Game(10);
    }

    public static GameRepository getRepository() {
        return INSTANCE;
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public void updateGameScore(int score) {
        game.setScore(game.getScore() + score);
    }

    @Override
    public int getGameScore() {
        return game.getScore();
    }

    @Override
    public int getIdOfCurrentQuestion() {
        return game.getCurrentId();
    }

    @Override
    public int getNextQuestionId() {
        return game.getNextId();
    }
}
