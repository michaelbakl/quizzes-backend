package it.sevenbits.quiz.core.repositories.room;

import it.sevenbits.quiz.core.model.Player;
import it.sevenbits.quiz.core.model.Room;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

/**
 * implementation of room repository using postgres database
 */
public class PostgresRoomRepository implements IRoomRepository {

  private final JdbcOperations jdbcOperations;

  /**
   * constructor
   * @param jdbcOperations - instrument for database
   */
  public PostgresRoomRepository(final JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  public void createRoom(final String roomId, final String roomName, final String ownerId) {
    jdbcOperations.update("INSERT INTO player (playerid, points) VALUES (?, ?) on conflict do nothing ",
              ownerId, 0);
    jdbcOperations.update("INSERT INTO room (roomid, roomname, ownerId) VALUES (?, ?, ?)",
            roomId, roomName, ownerId);
    jdbcOperations.update("INSERT INTO playersinroom (roomid, playerid) VALUES (?, ?)",
            roomId, ownerId);
  }

  @Override
  public List<Room> getAllRooms() {
    List<Room> rooms = jdbcOperations.query("SELECT * FROM room", (resultSet, i) ->
            new Room(resultSet.getString("roomid"),
                    resultSet.getString("roomname"),
                    resultSet.getString("ownerid")));
    for (Room room: rooms) {
      room.setPlayers(getPlayersInRoom(room.getRoomId()));
    }
    return rooms;
  }

  @Override
  public Room getRoomById(final String roomId) {
    Room room = jdbcOperations.queryForObject("SELECT * FROM room WHERE roomId = ?", (resultSet, i) ->
            new Room(resultSet.getString("roomid"),
                    resultSet.getString("roomname"),
                    resultSet.getString("ownerid")), roomId);
    assert room != null;
    List<Player> players = getPlayersInRoom(roomId);
    room.setPlayers(players);
    return room;
  }

  @Override
  public void addPlayer(final String roomId, final String playerId) {
    jdbcOperations.update("INSERT INTO player (playerid, points) VALUES (?, ?) on conflict do nothing",
              playerId, 0);

    jdbcOperations.update("INSERT INTO playersinroom (roomid, playerid) VALUES (?, ?)",
            roomId, playerId);
  }

  @Override
  public void updatePlayerScore(final String roomId, final String playerId, final int score) {
    int plScore = 0;
    try {
      plScore = jdbcOperations.queryForObject("SELECT points FROM player WHERE playerId = ?",
              (resultSet, i) -> resultSet.getInt("points"), playerId);
    } catch (NullPointerException e) {
      e.getMessage();
    }
    jdbcOperations.update("UPDATE player SET points = ? WHERE playerid = ?",
            score + plScore, playerId);
  }

  @Override
  public boolean checkRoomIsInRepository(final String roomId) {
    try {
      int checker = jdbcOperations.queryForObject("SELECT COUNT(roomid) AS counter FROM room WHERE roomid =?",
              (resultSet, i) -> resultSet.getInt("counter"), roomId);
      return checker == 1;
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public void deleteRoom(final String roomId) {
    jdbcOperations.update("DELETE FROM room WHERE roomid = ?", roomId);
  }


  private boolean checkPlayerExists(final String playerId) {
    try {
      int checker = jdbcOperations.queryForObject("SELECT COUNT(playerid) AS counter FROM player WHERE playerid =?",
              (resultSet, i) -> resultSet.getInt("counter"), playerId);
      return checker == 1;
    } catch (NullPointerException e) {
      return false;
    }
  }

  private List<Player> getPlayersInRoom(final String roomId) {
    return jdbcOperations.query("SELECT playerid FROM playersinroom WHERE roomid =?",
            (resultSet, i) -> new Player(resultSet.getString(1)), roomId);
  }
}
