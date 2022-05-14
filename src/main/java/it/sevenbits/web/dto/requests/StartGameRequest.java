package it.sevenbits.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * start game request class
 */
public class StartGameRequest {
  @JsonProperty
  private final String playerId;

  /**
   * constructor
   * @param playerId - String
   */
  @JsonCreator
  public StartGameRequest(@JsonProperty("playerId") final String playerId) {
    this.playerId = playerId;
  }
}
