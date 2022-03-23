package it.sevenbits.web.application.repositories;

import it.sevenbits.web.application.model.Game;

public interface IGameRepository {
    Game getGame();
    void updateGameScore(int score);
    int getGameScore();
    String getIdOfCurrentQuestion();
    String getNextQuestionId();
}
