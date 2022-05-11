package it.sevenbits.quiz.web.dto.requests.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * create room request class
 */
public class CreateRoomRequest {

  @JsonProperty
  private final String roomName;

  /**
   * constructor
   *
   * @param roomName - String
   */
  @JsonCreator
  public CreateRoomRequest(@JsonProperty("roomName") final String roomName) {
    this.roomName = roomName;
  }

  public String getRoomName() {
    return roomName;
  }
}
