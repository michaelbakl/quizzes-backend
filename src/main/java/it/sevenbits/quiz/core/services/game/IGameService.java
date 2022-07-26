package it.sevenbits.quiz.core.services.game;

import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.web.dto.responses.game.GetRulesResponse;
import it.sevenbits.quiz.web.dto.responses.question.AnswerQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.GameStatusResponse;
import it.sevenbits.quiz.web.dto.responses.question.GetQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.StartGameDtoResponse;

/**
 * interface for game service
 */
public interface IGameService {
  /**
   * start game method
   * @param roomId - String
   * @return StartGameDtoResponse
   * @throws QuizException - exception
   */
  StartGameDtoResponse startGame(String roomId) throws QuizException;

  /**
   * get question method
   * @param id - String
   * @return AnswerQuestionResponse
   * @throws QuizException - exception
   */
  GetQuestionResponse getQuestion(String id) throws QuizException;

  /**
   * send answer method
   * @param roomId - String
   * @param playerId - String
   * @param questionId - String
   * @param answerID - String
   * @return answer question response
   * @throws QuizException - exception
   */
  AnswerQuestionResponse sendAnswer(String roomId, String playerId, String questionId, String answerID) throws QuizException;

  /**
   *
   * @param roomId - String
   * @return GameStatusResponse
   * @throws QuizException - exception
   */
  GameStatusResponse getGameStatus(String roomId) throws QuizException;

  /**
   * checks if roomId is in repo
   * @param roomId - room id
   * @return true if room is in repo, false - otherwise
   */
  boolean checkRoomIsInRepo(String roomId);

  /**
   * return i of owner of the room
   * @param roomId - room id
   * @return owner id
   */
  String getOwnerId(String roomId);

  /**
   * return game rules
   * @return - response with game rules
   */
  GetRulesResponse getRules();

}
