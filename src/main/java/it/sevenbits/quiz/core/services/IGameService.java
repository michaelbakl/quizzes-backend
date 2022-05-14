package it.sevenbits.quiz.core.services;

import it.sevenbits.web.dto.responses.AnswerQuestionResponse;
import it.sevenbits.web.dto.responses.GetQuestionResponse;
import it.sevenbits.web.dto.responses.StartGameDtoResponse;

/**
 * interface for game service
 */
public interface IGameService {
  /**
   * start game method
   * @return start game dto response
   */
  StartGameDtoResponse startGame();

  /**
   * get question method
   * @param id - String
   * @return AnswerQuestionResponse
   */
  GetQuestionResponse getQuestion(String id);

  /**
   * send answer method
   * @param questionId - String
   * @param answerID - String
   * @return AnswerQuestionResponse
   */
  AnswerQuestionResponse sendAnswer(String questionId, String answerID);
}