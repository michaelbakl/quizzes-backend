package it.sevenbits.quiz.web.dto.responses.room;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * room info response class
 */
public class GetRoomInfoResponse {
  @JsonProperty
  private String roomId;
  @JsonProperty
  private String roomName;

  @JsonProperty
  private String ownerId;

  /**
   * constructor
   *
   * @param roomId - String (uuid)
   * @param roomName - String
   * @param ownerId - id of owner of the group
   */
  public GetRoomInfoResponse(@JsonProperty("roomId") final String roomId,
                             @JsonProperty("roomName") final String roomName,
                             @JsonProperty("ownerId") final String ownerId) {
    this.roomId = roomId;
    this.roomName = roomName;
    this.ownerId = ownerId;
  }

  public String getRoomId() {
    return roomId;
  }

  public String getRoomName() {
    return roomName;
  }

  public String getOwnerId() {
    return ownerId;
  }
}
