package it.sevenbits.quiz.web.dto.responses.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * game status response class
 */
public class GameStatusResponse {
  @JsonProperty
  private final String status;
  @JsonProperty
  private final String questionId;
  @JsonProperty
  private final int questionNumber;
  @JsonProperty
  private final int questionsCount;

  /**
   *
   * @param status - String
   * @param questionId - String
   * @param questionNumber - int
   * @param questionsCount - int
   */
  @JsonCreator
  public GameStatusResponse(@JsonProperty("status") final String status,
                            @JsonProperty("questionId") final String questionId,
                            @JsonProperty("questionNumber") final int questionNumber,
                            @JsonProperty("questionsCount") final int questionsCount) {
    this.status = status;
    this.questionId = questionId;
    this.questionNumber = questionNumber;
    this.questionsCount = questionsCount;
  }

  public String getStatus() {
    return status;
  }

  public String getQuestionId() {
    return questionId;
  }

  public int getQuestionNumber() {
    return questionNumber;
  }

  public int getQuestionsCount() {
    return questionsCount;
  }
}
