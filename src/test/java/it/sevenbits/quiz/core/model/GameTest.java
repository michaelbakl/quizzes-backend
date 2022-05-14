package it.sevenbits.quiz.core.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class GameTest extends TestCase {

  public void testGettersAndSetters() {
    Game game = new Game(10);
    assertEquals(10, game.getQuestionsAmount());
    game.setScore(10);
    game.setCurrentIdPos(0);
    List<String> questions = new ArrayList<>();
    questions.add("q1");
    questions.add("q2");
    questions.add("q3");
    game.setQuestionsIds(questions);

    assertEquals(10, game.getScore());
    assertEquals("q1", game.getCurrentQuestionId());
    assertEquals(0, game.getCurrentIdPos());
    assertEquals(questions, game.getQuestionsIds());

    questions.add("q4");
    game.setQuestionsIds(questions);
    assertEquals(questions, game.getQuestionsIds());
    assertEquals("q2", game.getNextId());
  }
}