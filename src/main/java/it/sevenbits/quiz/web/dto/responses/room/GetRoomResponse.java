package it.sevenbits.quiz.web.dto.responses.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.quiz.core.model.Player;

import java.util.List;

/**
 * get room response class
 */
public class GetRoomResponse {

  @JsonProperty
  private String roomId;

  @JsonProperty
  private String roomName;

  @JsonProperty
  private String ownerId;

  @JsonProperty
  private List<Player> players;

  /**
   * constructor
   * @param roomId - String
   * @param roomName - String
   * @param ownerId - String
   * @param players - List
   */
  public GetRoomResponse(@JsonProperty("roomId") final String roomId,
                         @JsonProperty("roomName") final String roomName,
                         @JsonProperty("ownerId") final String ownerId,
                         @JsonProperty("players") final List<Player> players) {
    this.roomId = roomId;
    this.roomName = roomName;
    this.ownerId = ownerId;
    this.players = players;
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

  public List<Player> getPlayers() {
    return players;
  }
}
