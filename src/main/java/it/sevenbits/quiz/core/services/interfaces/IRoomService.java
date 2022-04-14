package it.sevenbits.quiz.core.services.interfaces;

import it.sevenbits.web.dto.responses.GetRoomResponse;
import it.sevenbits.web.dto.responses.GetRoomsResponse;

/**
 * interface for room service
 */
public interface IRoomService {

  /**
   * get all rooms method
   * @return GetAllRoomsResponse
   */
  GetRoomsResponse getAllRooms();

  /**
   * creates room
   *
   * @param roomId - String
   * @param roomName - String
   * @return GetRoomResponse
   */
  GetRoomResponse createRoom(String roomId, String roomName);

  /**
   * get room by id
   *
   * @param roomId - String
   * @return GetRoomResponse
   */
  GetRoomResponse getRoomById(String roomId);

  /**
   * join room method
   *
   * @param roomId - String
   * @param playerId - String
   * @return GetRoomResponse
   */
  GetRoomResponse joinRoom(String roomId, String playerId);

}
