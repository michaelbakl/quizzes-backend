package it.sevenbits.quiz.core.services.interfaces;

import it.sevenbits.web.dto.responses.AnswerQuestionResponse;
import it.sevenbits.web.dto.responses.GameStatusResponse;
import it.sevenbits.web.dto.responses.GetQuestionResponse;
import it.sevenbits.web.dto.responses.StartGameDtoResponse;

/**
 * interface for game service
 */
public interface IGameService {
  /**
   * start game method
   * @param roomId - String
   * @return StartGameDtoResponse
   */
  StartGameDtoResponse startGame(String roomId);

  /**
   * get question method
   * @param id - String
   * @return AnswerQuestionResponse
   */
  GetQuestionResponse getQuestion(String id);

  /**
   * send answer method
   * @param roomId - String
   * @param playerId - String
   * @param questionId - String
   * @param answerID - String
   * @return answer question response
   */
  AnswerQuestionResponse sendAnswer(String roomId, String playerId, String questionId, String answerID);

  /**
   *
   * @param roomId - String
   * @return GameStatusResponse
   */
  GameStatusResponse getGameStatus(String roomId);

}
