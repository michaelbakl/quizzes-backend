package it.sevenbits.quiz.core.model;

import java.util.List;

/**
 * room class model
 */
public class Room {
  private String roomId;
  private String roomName;

  private String ownerId;
  private List<Player> players;


  /**
   * constructor
   * @param roomId - room id
   * @param roomName - room name
   * @param ownerId - id of the owner of the room
   */
  public Room(final String roomId, final String roomName, final String ownerId) {
    this.roomId = roomId;
    this.roomName = roomName;
    this.ownerId = ownerId;
  }

  /**
   * constructor
   *
   * @param roomId - String
   * @param roomName - String
   * @param ownerId - String
   * @param players - List
   */
  public Room(final String roomId, final String roomName, final String ownerId, final List<Player> players) {
    this.roomId = roomId;
    this.roomName = roomName;
    this.ownerId = ownerId;
    this.players = players;
  }

  public String getRoomId() {
    return roomId;
  }

  public void setRoomId(final String roomId) {
    this.roomId = roomId;
  }

  public String getRoomName() {
    return roomName;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(final String ownerId) {
    this.ownerId = ownerId;
  }

  public void setRoomName(final String roomName) {
    this.roomName = roomName;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(final List<Player> players) {
    this.players = players;
  }

  /**
   * add player
   * @param player - Player
   */
  public void addPlayer(final Player player) {
    players.add(player);
  }

  /**
   * return player by his id
   * @param playerId - String
   * @return player
   */
  public Player getPlayerById(final String playerId) {
    for (Player player: players) {
      if (player.getPlayerId().equals(playerId)) {
        return player;
      }
    }
    return null;
  }

}
