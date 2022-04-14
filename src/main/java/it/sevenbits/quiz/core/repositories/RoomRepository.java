package it.sevenbits.quiz.core.repositories;

import it.sevenbits.quiz.core.model.Player;
import it.sevenbits.quiz.core.model.Room;
import it.sevenbits.quiz.core.repositories.interfaces.IRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * room repository
 */
@Repository
public final class RoomRepository implements IRoomRepository {

  private static RoomRepository repository;
  private final Map<String, Room> rooms;

  private RoomRepository() {
    rooms = new HashMap<>();
  }

  /**
   * singlton getter
   *
   * @return RoomRepository
   */
  public static RoomRepository getRepository() {
    if (repository == null) {
      repository = new RoomRepository();
    }
    return repository;
  }

  @Override
  public void createRoom(final String roomId, final String roomName) {
    Room room = new Room(roomId, roomName);
    rooms.put(roomId, room);
  }

  @Override
  public List<Room> getAllRooms() {
    return new ArrayList<>(rooms.values());
  }

  @Override
  public Room getRoomById(final String roomId) {
    return rooms.get(roomId);
  }

  @Override
  public void addPlayer(final String roomId, final String playerId) {
    getRoomById(roomId).addPlayer(new Player(playerId));
  }

}
