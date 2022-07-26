package it.sevenbits.quiz.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * login exception class
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginFailedException extends RuntimeException {

  /**
   * constructor
   * @param message - message of error
   * @param cause - cause of error
   */
  public LoginFailedException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * @param message - message of error
   */
  public LoginFailedException(final String message) {
    super(message);
  }

}

