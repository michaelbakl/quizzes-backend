package it.sevenbits.quiz.core.exceptions;

/**
 * quiz exception class
 */
public class QuizException extends Exception {
  private final QuizErrorCode errorCode;

  /**
   * constructor
   * @param code - error enum code for exception
   */
  public QuizException(final QuizErrorCode code) {
    errorCode = code;
  }

  public QuizErrorCode getErrorCode() {
    return errorCode;
  }


}
