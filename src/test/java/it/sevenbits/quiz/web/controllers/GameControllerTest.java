package it.sevenbits.quiz.web.controllers;

import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.services.game.GameService;
import it.sevenbits.quiz.core.services.game.IGameService;
import it.sevenbits.quiz.web.dto.requests.question.AnswerQuestionRequest;
import it.sevenbits.quiz.web.dto.responses.question.AnswerQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.GameStatusResponse;
import it.sevenbits.quiz.web.dto.responses.question.GetQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.StartGameDtoResponse;
import it.sevenbits.quiz.web.security.UserCredentials;
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
    UserCredentials mockUserInfo = mock(UserCredentials.class);
    when(mockUserInfo.getUserId()).thenReturn("7df2eb5c-d119-11ec-9d64-0242ac120002");
    when(mockService.getOwnerId(anyString())).thenReturn("7df2eb5c-d119-11ec-9d64-0242ac120002");
    ResponseEntity<StartGameDtoResponse> answer =
            gameController.startGame("7df2eb5c-d119-11ec-9d64-0242ac120002", mockUserInfo);

    verify(mockService, times(1)).startGame(anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }

  @Test
  public void getQuestion() throws QuizException {
    GetQuestionResponse mockResponse = mock(GetQuestionResponse.class);
    when(mockService.getQuestion(anyString())).thenReturn(mockResponse);

    ResponseEntity<GetQuestionResponse> answer =
            gameController.getQuestion("7df2eb5c-d119-11ec-9d64-0242ac120002",
                    "8df2eb5c-d119-11ec-9d64-0242ac120002");

    verify(mockService, times(1)).getQuestion(anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }

  @Test
  public void sendAnswer() throws QuizException {
    AnswerQuestionResponse mockResponse = mock(AnswerQuestionResponse.class);
    when(mockService.sendAnswer(anyString(), anyString(), anyString(), anyString())).thenReturn(mockResponse);
    UserCredentials mockUserInfo = mock(UserCredentials.class);
    when(mockUserInfo.getUserId()).thenReturn("fb8f62ef-cd79-49e7-96de-12ff122f2aaf");
    AnswerQuestionRequest request = new AnswerQuestionRequest("fb8f62ef-cd79-49e7-96de-12ff122f2aaf");

    ResponseEntity<AnswerQuestionResponse> answer =
            gameController.sendAnswer(request,
                    "fb8f62ef-cd79-49e7-96de-12ff122f2aaf",
                    "fb8f62ef-cd79-49e7-96de-12ff122f2aaf",
                    mockUserInfo
            );

    verify(mockService, times(1)).sendAnswer(anyString(), anyString(), anyString(), anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }

  @Test
  public void getGameStatus() throws QuizException {
    GameStatusResponse mockResponse = mock(GameStatusResponse.class);
    when(mockService.getGameStatus(anyString())).thenReturn(mockResponse);
    ResponseEntity<GameStatusResponse> answer = gameController.getGameStatus("7df2eb5c-d119-11ec-9d64-0242ac120002");

    verify(mockService, times(1)).getGameStatus(anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }
}