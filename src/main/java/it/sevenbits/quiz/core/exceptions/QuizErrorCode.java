package it.sevenbits.quiz.core.exceptions;

/**
 * error codes for exceptions
 */
public enum QuizErrorCode {
  /**
   */
  QUESTION_NOT_FOUND("Question was not found in the database"),
  /**
   */
  ROOM_NOT_FOUND("Room was not found in the database"),
  /**
   */
  WRONG_INPUTS("Wrong inputs"),
  /**
   */
  PLAYER_IS_NOT_IN_THE_ROOM("Player was not found in the room"),
  /**
   */
  ANSWER_DOES_NOT_MATCH_QUESTION("Answer does not belong to the question player tries to answer");

  private final String errorString;

  /**
   * constructor
   * @param errorString - error string
   */
  QuizErrorCode(final String errorString) {
    this.errorString = errorString;
  }

  public String getErrorString() {
    return errorString;
  }

}