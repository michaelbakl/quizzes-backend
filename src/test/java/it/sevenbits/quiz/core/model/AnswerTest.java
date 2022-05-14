package it.sevenbits.quiz.core.model;


import org.junit.Assert;
import org.junit.Test;

public class AnswerTest {

  @Test
  public void gettersTest() {
    Answer answer = new Answer("unique_id", "Content", 10);

    Assert.assertEquals("unique_id", answer.getAnswerId());
    Assert.assertEquals("Content", answer.getAnswerText());
    Assert.assertEquals(10, answer.getPoints());
  }

  @Test
  public void settersTest() {
    Answer answer = new Answer("unique_id", "Content", 10);

    Assert.assertEquals("unique_id", answer.getAnswerId());
    Assert.assertEquals("Content", answer.getAnswerText());
    Assert.assertEquals(10, answer.getPoints());

    answer.setAnswerId("new_unique_id");
    answer.setAnswerText("New_content");
    answer.setPoints(20);

    Assert.assertEquals("new_unique_id", answer.getAnswerId());
    Assert.assertEquals("New_content", answer.getAnswerText());
    Assert.assertEquals(20, answer.getPoints());
  }

}