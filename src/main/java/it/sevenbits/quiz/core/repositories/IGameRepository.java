package it.sevenbits.quiz.core.repositories;

import it.sevenbits.quiz.core.model.Game;

/**
 * interface for game repository
 */
public interface IGameRepository {
    /**
     * get game method
     * @return Game
     */
    Game getGame();

    /**
     * updates game score
     * @param score - int
     */
    void updateGameScore(int score);

    /**
     * get game score method
     * @return int
     */
    int getGameScore();

    /**
     * get id of current question method
     * @return String
     */
    String getIdOfCurrentQuestion();

    /**
     * get next question id method
     * @return String
     */
    String getNextQuestionId();
}
