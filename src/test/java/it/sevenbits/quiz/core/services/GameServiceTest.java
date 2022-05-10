package it.sevenbits.quiz.core.services;

import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.model.Answer;
import it.sevenbits.quiz.core.model.Game;
import it.sevenbits.quiz.core.model.Question;
import it.sevenbits.quiz.core.model.Room;
import it.sevenbits.quiz.core.repositories.game.GameRepository;
import it.sevenbits.quiz.core.repositories.question.MapQuestionRepository;
import it.sevenbits.quiz.core.repositories.room.RoomRepository;
import it.sevenbits.quiz.core.services.game.GameService;
import it.sevenbits.quiz.web.dto.responses.question.AnswerQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.GameStatusResponse;
import it.sevenbits.quiz.web.dto.responses.question.GetQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.StartGameDtoResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameServiceTest {
  GameRepository mockGameRepository;
  MapQuestionRepository mockQuestionRepository;

  RoomRepository mockRoomRepository;
  GameService gameService;


  @Before
  public void setup() {
    mockGameRepository = mock(GameRepository.class);
    mockQuestionRepository = mock(MapQuestionRepository.class);
    mockRoomRepository = mock(RoomRepository.class);
    gameService = new GameService(mockGameRepository, mockQuestionRepository, mockRoomRepository);
  }

  @Test
  public void startGame() throws QuizException {
    List<String> list = new ArrayList<>();
    list.add("1");
    when(mockQuestionRepository.getListOfRandomQuestionsIds(anyInt())).thenReturn(list);
    when(mockRoomRepository.checkRoomIsInRepository(anyString())).thenReturn(true);

    StartGameDtoResponse response = gameService.startGame("1");
    assertNotNull(response.getQuestionId());
  }

  @Test
  public void getQuestion() throws QuizException {
    Question mockQuestion = mock(Question.class);
    List<Answer> mockList = mock(List.class);
    when(mockQuestionRepository.getQuestion(anyString())).thenReturn(mockQuestion);
    when(mockQuestion.getAnswers()).thenReturn(mockList);

    GetQuestionResponse response = gameService.getQuestion("1");
    assertNotNull(response);
  }

  @Test
  public void sendAnswer() throws QuizException {
    Question mockQuestion = mock(Question.class);
    when(mockQuestionRepository.getQuestion(anyString())).thenReturn(mockQuestion);
    when(mockRoomRepository.checkRoomIsInRepository(anyString())).thenReturn(true);
    String roomId = "roomId";
    List<String> list = new ArrayList<>();
    list.add("1");
    List<String> answers = new ArrayList<>();
    answers.add("answerId");
    when(mockQuestionRepository.getListOfRandomQuestionsIds(anyInt())).thenReturn(list);
    gameService.startGame(roomId);
    when(mockQuestion.getCorrectAnswer()).thenReturn(new Answer("answerId", "dlmd", 10));
    when(mockRoomRepository.getRoomById(anyString())).thenReturn(new Room(roomId, roomId));
    when(mockGameRepository.getNextQuestionId(anyString())).thenReturn("Next");
    when(mockGameRepository.getGameScore(anyString())).thenReturn(10);
    when(mockQuestionRepository.getQuestion(anyString())).thenReturn(mockQuestion);
    when(mockRoomRepository.checkRoomIsInRepository(anyString())).thenReturn(true);
    when(mockQuestion.getAnswersIds()).thenReturn(answers);
    
    AnswerQuestionResponse response = gameService.sendAnswer(roomId, roomId, "bj", "answerId");

    assertEquals("Next", response.getQuestionId());
    assertEquals("answerId", response.getCorrectAnswerId());
    assertEquals(10, response.getQuestionScore());
    assertEquals(10, response.getTotalScore());
  }

  @Test
  public void getGameStatus() throws QuizException {
    Question mockQuestion = mock(Question.class);
    Game mockGame = mock(Game.class);
    when(mockQuestionRepository.getQuestion(anyString())).thenReturn(mockQuestion);
    when(mockRoomRepository.checkRoomIsInRepository(anyString())).thenReturn(true);
    String roomId = "roomId";
    List<String> list = new ArrayList<>();
    list.add("1");
    when(mockQuestionRepository.getListOfRandomQuestionsIds(anyInt())).thenReturn(list);
    gameService.startGame(roomId);

    when(mockGameRepository.getIdOfCurrentQuestion(anyString())).thenReturn("1");
    when(mockGameRepository.getGame(anyString())).thenReturn(mockGame);
    when(mockGame.getCurrentIdPos()).thenReturn(1);
    when(mockGame.getQuestionsAmount()).thenReturn(10);
    when(mockGame.getStatus()).thenReturn("ok");

    GameStatusResponse response = gameService.getGameStatus(roomId);

    assertEquals("1", response.getQuestionId());
    assertEquals("ok", response.getStatus());
    assertEquals(1, response.getQuestionNumber());
    assertEquals(10, response.getQuestionsCount());
  }
}