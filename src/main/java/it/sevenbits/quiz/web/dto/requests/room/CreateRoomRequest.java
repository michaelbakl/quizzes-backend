package it.sevenbits.quiz.web.dto.requests.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * create room request class
 */
public class CreateRoomRequest {
  @JsonProperty
  private final String playerId;
  @JsonProperty
  private final String roomName;

  /**
   * constructor
   *
   * @param playerId - String
   * @param roomName - String
   */
  @JsonCreator
  public CreateRoomRequest(@JsonProperty("playerId") final String playerId,
                           @JsonProperty("roomName") final String roomName) {
    this.playerId = playerId;
    this.roomName = roomName;
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getRoomName() {
    return roomName;
  }
}
