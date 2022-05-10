package it.sevenbits.quiz.web.controllers;

import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.services.game.GameService;
import it.sevenbits.quiz.core.services.interfaces.IGameService;
import it.sevenbits.quiz.web.dto.requests.question.AnswerQuestionRequest;
import it.sevenbits.quiz.web.dto.responses.question.AnswerQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.GameStatusResponse;
import it.sevenbits.quiz.web.dto.responses.question.GetQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.StartGameDtoResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GameControllerTest {
  private IGameService mockService;
  private GameController gameController;

  @Before
  public void setup() {
    mockService = mock(GameService.class);
    gameController = new GameController(mockService);
  }

  @Test
  public void startGame() throws QuizException {
    StartGameDtoResponse mockResponse = mock(StartGameDtoResponse.class);
    when(mockService.startGame(anyString())).thenReturn(mockResponse);

    ResponseEntity<StartGameDtoResponse> answer = gameController.startGame("1");

    verify(mockService, times(1)).startGame(anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }

  @Test
  public void getQuestion() throws QuizException {
    GetQuestionResponse mockResponse = mock(GetQuestionResponse.class);
    when(mockService.getQuestion(anyString())).thenReturn(mockResponse);

    ResponseEntity<GetQuestionResponse> answer = gameController.getQuestion("1", "1");

    verify(mockService, times(1)).getQuestion(anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }

  @Test
  public void sendAnswer() throws QuizException {
    AnswerQuestionResponse mockResponse = mock(AnswerQuestionResponse.class);
    when(mockService.sendAnswer(anyString(), anyString(), anyString(), anyString())).thenReturn(mockResponse);
    AnswerQuestionRequest request = new AnswerQuestionRequest("1", "1");

    ResponseEntity<AnswerQuestionResponse> answer = gameController.sendAnswer(request, "1", "1");

    verify(mockService, times(1)).sendAnswer(anyString(), anyString(), anyString(), anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }

  @Test
  public void getGameStatus() throws QuizException {
    GameStatusResponse mockResponse = mock(GameStatusResponse.class);
    when(mockService.getGameStatus(anyString())).thenReturn(mockResponse);
    ResponseEntity<GameStatusResponse> answer = gameController.getGameStatus("1");

    verify(mockService, times(1)).getGameStatus(anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }
}