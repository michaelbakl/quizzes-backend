package it.sevenbits.quiz.web.dto.responses;

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
  private List<Player> players;

  /**
   * constructor
   * @param roomId - String
   * @param roomName - String
   * @param players - List
   */
  public GetRoomResponse(@JsonProperty("roomId") final String roomId,
                         @JsonProperty("roomName") final String roomName,
                         @JsonProperty("players") final List<Player> players) {
    this.roomId = roomId;
    this.roomName = roomName;
    this.players = players;
  }

  public String getRoomId() {
    return roomId;
  }

  public String getRoomName() {
    return roomName;
  }

  public List<Player> getPlayers() {
    return players;
  }
}
