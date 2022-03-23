package it.sevenbits.web.application.repositories;

import it.sevenbits.web.application.model.Game;

public class GameRepository implements IGameRepository {
    private Game game;

    public GameRepository() {
        game = new Game(10);
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
    public String getIdOfCurrentQuestion() {
        return game.getCurrentQuestionId();
    }

    @Override
    public String getNextQuestionId() {
        return game.getNextId();
    }
}
