package it.sevenbits.quiz.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model to send token.
 */
public class Token {

  private final String token;

  /**
   * constructor
   * @param token - token
   */
  @JsonCreator
  public Token(@JsonProperty("token") final String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

}

