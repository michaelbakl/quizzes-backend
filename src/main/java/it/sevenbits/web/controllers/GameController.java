package it.sevenbits.web.controllers;

import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.services.interfaces.IGameService;
import it.sevenbits.web.dto.requests.AnswerQuestionRequest;
import it.sevenbits.web.dto.responses.AnswerQuestionResponse;
import it.sevenbits.web.dto.responses.GameStatusResponse;
import it.sevenbits.web.dto.responses.GetQuestionResponse;
import it.sevenbits.web.dto.responses.StartGameDtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Game controller
 */
@Controller
@RequestMapping("/rooms/{roomId}/game")
public class GameController {

    private final IGameService gameService;

    /**
     * constructor
     *
     * @param gameService - GameService
     */
    public GameController(final IGameService gameService) {
        this.gameService = gameService;
    }

    /**
     * starts game
     * @param roomId - String
     * @return StartGameDtoResponse
     */
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    ResponseEntity<StartGameDtoResponse> startGame(@PathVariable("roomId") final String roomId) {
        try {
            StartGameDtoResponse response = gameService.startGame(roomId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (QuizException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * get question method: gets question from repository and returns it
     * @param roomId - String
     * @param questionId - String
     * @return GetQuestionResponse
     */
    @RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GetQuestionResponse> getQuestion(@PathVariable("roomId") final String roomId,
                                                           @PathVariable("questionId") final String questionId) {
        try {
            GetQuestionResponse questionResponse = gameService.getQuestion(questionId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(questionResponse);
        } catch (QuizException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * sends answer to a question
     * @param questionId - String
     * @param roomId - String
     * @param answerQuestionRequest - AnswerQuestionRequest
     * @return ResponseEntity
     */
    @RequestMapping(value = "/question/{questionId}/answer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AnswerQuestionResponse> sendAnswer(@RequestBody final AnswerQuestionRequest
                                                                        answerQuestionRequest,
                                                             @PathVariable("roomId") final String roomId,
                                                             @PathVariable("questionId") final String questionId
                                                            ) {
        try {
            AnswerQuestionResponse response =
                    gameService.sendAnswer(roomId,
                            answerQuestionRequest.getPlayerId(),
                            questionId,
                            answerQuestionRequest.getAnswerId());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (QuizException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * gets game status
     * @param roomId - String
     * @return GameStatusResponse
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GameStatusResponse> getGameStatus(@PathVariable("roomId") final String roomId) {
        try {
            GameStatusResponse gameStatusResponse = gameService.getGameStatus(roomId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(gameStatusResponse);
        } catch (QuizException e) {
            GameStatusResponse gameStatusResponse =
                    new GameStatusResponse("INVALID", "INNVALID", -1, -1);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(gameStatusResponse);
        }
    }

}
