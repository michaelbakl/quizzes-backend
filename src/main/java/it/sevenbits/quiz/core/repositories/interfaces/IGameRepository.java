package it.sevenbits.quiz.core.repositories.interfaces;

import it.sevenbits.quiz.core.model.Game;

/**
 * interface for game repository
 */
public interface IGameRepository {
    /**
     * get game by room id method
     * @param roomId - String
     * @return game
     */
    Game getGame(String roomId);

    /**
     * creates game by room id method
     * @param roomId - String
     * @param game - Game
     */
    void createGame(String roomId, Game game);

    /**
     *
     * @param score - int
     * @param roomId - String
     */
    void updateGameScore(int score, String roomId);

    /**
     *
     * @param roomId - String
     * @return int
     */
    int getGameScore(String roomId);

    /**
     *
     * @param roomId - String
     * @return String
     */
    String getIdOfCurrentQuestion(String roomId);

    /**
     *
     * @param roomId - String
     * @return String
     */
    String getNextQuestionId(String roomId);
}
