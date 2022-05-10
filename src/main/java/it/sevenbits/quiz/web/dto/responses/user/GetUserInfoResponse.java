package it.sevenbits.quiz.web.dto.responses.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * get info about user response class
 */
public class GetUserInfoResponse {

  @JsonProperty
  private String userId;

  @JsonProperty
  private String email;

  @JsonProperty
  private List<String> roles;

  /**
   * constructor
   * @param userId - user id
   * @param email - email
   * @param roles - roles
   */
  @JsonCreator
  public GetUserInfoResponse(@JsonProperty("userId") final String userId,
                             @JsonProperty("email") final String email,
                             @JsonProperty("roles") final List<String> roles) {
    this.userId = userId;
    this.email = email;
    this.roles = roles;
  }
}
