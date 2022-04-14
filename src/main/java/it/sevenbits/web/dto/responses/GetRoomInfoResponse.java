package it.sevenbits.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * room info response class
 */
public class GetRoomInfoResponse {
  @JsonProperty
  private String roomId;
  @JsonProperty
  private String roomName;

  /**
   * constructor
   *
   * @param roomId - String (uuid)
   * @param roomName - String
   */
  public GetRoomInfoResponse(@JsonProperty("roomId") final String roomId,
                             @JsonProperty("roomName") final String roomName) {
    this.roomId = roomId;
    this.roomName = roomName;
  }

  public String getRoomId() {
    return roomId;
  }

  public String getRoomName() {
    return roomName;
  }
}
