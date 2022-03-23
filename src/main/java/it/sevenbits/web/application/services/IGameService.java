package it.sevenbits.web.application.services;

import it.sevenbits.web.application.dto.responses.SendAnswerDtoResponse;
import it.sevenbits.web.application.dto.responses.StartGameDtoResponse;
import it.sevenbits.web.application.model.Question;

/**
 * interface for game service
 */
public interface IGameService {
    /**
     * start game method
     * @return StartGameDtoResponse
     */
    StartGameDtoResponse startGame();

    /**
     * getter for question
     * @param id - String
     * @return Question
     */
    Question getQuestion(String id);

    /**
     * send answer method
     * @param questionId - String
     * @param answerID - String
     * @return SendAnswerDtoResponse
     */
    SendAnswerDtoResponse sendAnswer(String questionId, String answerID);

    /**
     * validate answer method
     * @param id - String
     */
    void validateAnswer(String id);
}
