package it.sevenbits.quiz.core.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class QuestionTest extends TestCase {

  public void testGettersAndSetters() {
    List<Answer> answerList = new ArrayList<>();
    Answer mockAnswer = mock(Answer.class);
    Answer expectedAnswer = new Answer("10", "10", 10);
    answerList.add(mockAnswer);
    answerList.add(mockAnswer);
    answerList.add(mockAnswer);
    Question question = new Question("id", "content", answerList, mockAnswer);
    assertEquals("id", question.getId());
    assertEquals("content", question.getContent());
    assertEquals(answerList, question.getAnswers());
    assertEquals(mockAnswer, question.getCorrectAnswer());

    answerList.add(expectedAnswer);

    question.setContent("c");
    question.setCorrectAnswer(expectedAnswer);
    question.setAnswers(answerList);

    assertEquals("c", question.getContent());
    assertEquals(expectedAnswer, question.getCorrectAnswer());
    assertEquals(answerList, question.getAnswers());
  }
}