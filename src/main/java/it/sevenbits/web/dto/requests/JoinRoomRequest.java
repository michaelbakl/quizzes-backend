package it.sevenbits.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * join room request class
 */
public class JoinRoomRequest {
  @JsonProperty
  private final String playerId;

  /**
   * constructor
   *
   * @param playerId - String
   */
  @JsonCreator
  public JoinRoomRequest(@JsonProperty("playerId") final String playerId) {
    this.playerId = playerId;
  }

  public String getPlayerId() {
    return playerId;
  }
}
