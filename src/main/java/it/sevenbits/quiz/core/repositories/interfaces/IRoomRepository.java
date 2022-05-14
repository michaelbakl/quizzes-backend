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

  /**
   * adds score to player`s score
   * @param roomId - String
   * @param playerId - String
   * @param score - String
   */
  void updatePlayerScore(String roomId, String playerId, int score);

  /**
   * checks if room is in repository
   * @param roomId - room id
   * @return true if room exists, false otherwise
   */
  boolean checkRoomIsInRepository(String roomId);
}
