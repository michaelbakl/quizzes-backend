package it.sevenbits.quiz.core.repositories;

import it.sevenbits.quiz.core.model.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameRepositoryTest {
  GameRepository repository;

  @Before
  public void setup() {
    repository = new GameRepository();
  }

  @Test
  public void getGameTest() {
    Game mockGame = mock(Game.class);
    repository.createGame("1", mockGame);
    assertEquals(mockGame, repository.getGame("1"));
  }

  @Test
  public void createGameTest() {
    Game mockGame = mock(Game.class);
    repository.createGame("1", mockGame);
    assertEquals(mockGame, repository.getGame("1"));
  }

  @Test
  public void updateGameScoreTest() {
    Game game = new Game(10);
    repository.createGame("1", game);
    repository.updateGameScore(10, "1");
    assertEquals(10, repository.getGameScore("1"));
  }

  @Test
  public void getIdOfCurrentQuestionTest() {
    Game mockGame = mock(Game.class);
    repository.createGame("1", mockGame);
    when(mockGame.getCurrentQuestionId()).thenReturn("test");
    assertEquals("test", repository.getIdOfCurrentQuestion("1"));
  }

  @Test
  public void getNextQuestionIdTest() {
    Game mockGame = mock(Game.class);
    repository.createGame("1", mockGame);
    when(mockGame.getNextId()).thenReturn("test2");
    assertEquals("test2", repository.getNextQuestionId("1"));
  }
}