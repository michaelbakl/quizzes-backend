package it.sevenbits.web.application.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.web.application.dto.responses.AnswerQuestionRequest;
import it.sevenbits.web.application.dto.responses.SendAnswerDtoResponse;
import it.sevenbits.web.application.dto.responses.StartGameDtoResponse;
import it.sevenbits.web.application.model.Question;
import it.sevenbits.web.application.services.IGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rooms/1/game")
public class GameController {
    private final IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/start")
    ResponseEntity<StartGameDtoResponse> startGame() {
        StartGameDtoResponse response = gameService.startGame();
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Question getQuestion(@PathVariable("id") String id) {
        return gameService.getQuestion(id);
    }

    @RequestMapping(value = "/questions/{id}/answer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SendAnswerDtoResponse> sendAnswer(@JsonProperty("id") String questionId,
                                                            @RequestBody AnswerQuestionRequest answerQuestionRequest) {
        gameService.sendAnswer(questionId, answerQuestionRequest.getId());
        SendAnswerDtoResponse response = gameService.sendAnswer(questionId, answerQuestionRequest.getId());
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello, test";
    }


}


