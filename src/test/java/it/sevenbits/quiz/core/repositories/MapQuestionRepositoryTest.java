package it.sevenbits.quiz.core.repositories;

import it.sevenbits.quiz.core.model.Question;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MapQuestionRepositoryTest {
  MapQuestionRepository repository;
  @Before
  public void setup() {
    repository = new MapQuestionRepository();
  }

  @Test
  public void getQuestion() {
    Question question = repository.getRandomQuestion();
    assertEquals(question, repository.getQuestion(question.getId()));
  }

  @Test
  public void getRandomQuestion() {
    assertNotNull(repository.getRandomQuestion());
  }

  @Test
  public void getListOfRandomQuestion() {
    List<Question> actual = repository.getListOfRandomQuestion(10);
    assertNotNull(actual);
    assertEquals(10, actual.size());
  }

  @Test
  public void getListOfRandomQuestionsIds() {
    List<String> actualIds = repository.getListOfRandomQuestionsIds(10);
    assertNotNull(actualIds);
    assertEquals(10, actualIds.size());
  }
}