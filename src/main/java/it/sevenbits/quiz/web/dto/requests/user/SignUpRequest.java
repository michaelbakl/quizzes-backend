package it.sevenbits.quiz.web.dto.requests.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * sign up request class
 */
public class SignUpRequest {

  @JsonProperty
  private String email;

  @JsonProperty
  private String password;

  /**
   * constructor
   * @param email - email
   * @param password - password
   */
  @JsonCreator
  public SignUpRequest(@JsonProperty("email") final String email,
                       @JsonProperty("password") final String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }
}
