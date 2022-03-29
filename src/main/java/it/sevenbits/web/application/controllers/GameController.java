package it.sevenbits.web.application.controllers;

import it.sevenbits.web.application.dto.requests.AnswerQuestionRequest;
import it.sevenbits.web.application.dto.responses.SendAnswerDtoResponse;
import it.sevenbits.web.application.dto.responses.StartGameDtoResponse;
import it.sevenbits.web.application.model.Question;
import it.sevenbits.web.application.services.IGameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Game controller
 */
@RestController
@RequestMapping("/rooms/{roomId}/game")
public class GameController {
    private final IGameService gameService;

    /**
     * constructor
     *
     * @param gameService - IGameService
     */
    public GameController(final IGameService gameService) {
        this.gameService = gameService;
    }

    /**
     * startGame method
     * @return ResponseEntity
     */
    @RequestMapping("/start")
    ResponseEntity<StartGameDtoResponse> startGame(@PathVariable("roomId") final String roomId) {
        try {
            StartGameDtoResponse response = gameService.startGame();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * get question method
     * @param id - String
     * @return Question
     */
    @RequestMapping(value = "/questions/{questionId}", method = RequestMethod.GET)
    @ResponseBody
    public Question getQuestion(@PathVariable("roomId") final String roomId,
                                @PathVariable("questionId") final String id) {
        try {
            return gameService.getQuestion(id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * @param questionId - String
     * @param answerQuestionRequest - AnswerQuestionRequest
     * @return ResponseEntity
     */
    @RequestMapping(value = "/questions/{questionId}/answer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SendAnswerDtoResponse> sendAnswer(@RequestBody final AnswerQuestionRequest
                                                                        answerQuestionRequest,
                                                            @PathVariable("roomId") final String roomId,
                                                            @PathVariable("questionId") final String questionId
                                                            ) {
        try {
            SendAnswerDtoResponse response = gameService.sendAnswer(questionId, answerQuestionRequest.getId());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


