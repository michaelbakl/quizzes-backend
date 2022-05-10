package it.sevenbits.quiz.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model to receive username and password.
 */
public class Login {
  @JsonProperty
  private final String login;

  @JsonProperty
  private final String password;

  /**
   * constructor
   * @param login - email
   * @param password - password
   */
  @JsonCreator
  public Login(@JsonProperty("login") final String login,
               @JsonProperty("password") final String password) {
    this.login = login;
    this.password = password;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

}

