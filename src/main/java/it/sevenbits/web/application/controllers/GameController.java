package it.sevenbits.web.application.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@RequestMapping("/rooms/1/game")
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
    ResponseEntity<StartGameDtoResponse> startGame() {
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
    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Question getQuestion(@PathVariable("id") final String id) {
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
    public ResponseEntity<SendAnswerDtoResponse> sendAnswer(@JsonProperty("questionId") final String questionId,
                                                            @RequestBody final AnswerQuestionRequest
                                                                    answerQuestionRequest) {
        try {
            SendAnswerDtoResponse response = gameService.sendAnswer(questionId, answerQuestionRequest.getId());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


