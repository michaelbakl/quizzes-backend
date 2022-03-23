package it.sevenbits.web.application.controllers;

import it.sevenbits.web.application.dto.responses.SendAnswerDtoResponse;
import it.sevenbits.web.application.dto.responses.StartGameDtoResponse;
import it.sevenbits.web.application.model.Question;
import it.sevenbits.web.application.services.IGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/game")
public class GameController {
    private final IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/start")
    ResponseEntity<StartGameDtoResponse> startGame() {
        StartGameDtoResponse response = gameService.startGame();
        URI location = UriComponentsBuilder.fromPath("/game/")
                .path(String.valueOf(response.questionId))
                .build().toUri();
        return ResponseEntity.created(location).body(response);
    }
    @RequestMapping(value = "/questions/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Question getQuestion(@PathVariable("id") int id) {
        return gameService.getQuestion(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SendAnswerDtoResponse> sendAnswer(int questionId, int answerId) {
        gameService.sendAnswer(questionId, answerId);
        SendAnswerDtoResponse response = gameService.sendAnswer(questionId, answerId);
        URI location = UriComponentsBuilder.fromPath("/game/")
                .path(String.valueOf(response.nextQuestionId))
                .build().toUri();
        return ResponseEntity.created(location).body(response);
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello, test";
    }


}


