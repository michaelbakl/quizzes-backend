package it.sevenbits.quiz.core.repositories.interfaces;

import it.sevenbits.quiz.core.model.Room;

import java.util.List;

/**
 * interface fom room repository
 */
public interface IRoomRepository {
  /**
   * creates a room
   *
   * @param roomId - String
   * @param roomName - String
   */
  void createRoom(String roomId, String roomName);

  /**
   * get all rooms method
   * @return GetRoomsResponse
   */
  List<Room> getAllRooms();

  /**
   * get room by id method
   * @param roomId - String
   * @return Room
   */
  Room getRoomById(String roomId);

  /**
   * add player method
   * @param roomId - String
   * @param playerId - String
   */
  void addPlayer(String roomId, String playerId);
}
