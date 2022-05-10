package it.sevenbits.quiz.web.dto.responses.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * sign in response
 */
public class SignInResponse {
  @JsonProperty
  private final String accessToken;

  /**
   * constructor
   * @param accessToken - token
   */
  @JsonCreator
  public SignInResponse(final String accessToken) {
    this.accessToken = accessToken;
  }

  public String getAccessToken() {
    return accessToken;
  }
}
