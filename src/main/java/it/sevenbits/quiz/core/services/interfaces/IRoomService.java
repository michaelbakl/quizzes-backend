package it.sevenbits.quiz.core.services.interfaces;

import it.sevenbits.quiz.core.exceptions.QuizException;
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
   * @param roomId - room id
   * @param roomName - room name
   * @return GetRoomResponse
   * * @throws QuizException - exception
   */
  GetRoomResponse createRoom(String roomId, String roomName) throws QuizException;

  /**
   * get room by id
   *
   * @param roomId - room id
   * @return GetRoomResponse
   * @throws QuizException - exception
   */
  GetRoomResponse getRoomById(String roomId) throws QuizException;

  /**
   * join room method
   *
   * @param roomId - String
   * @param playerId - String
   * @return GetRoomResponse
   * @throws QuizException - exception
   */
  GetRoomResponse joinRoom(String roomId, String playerId) throws QuizException;


}
