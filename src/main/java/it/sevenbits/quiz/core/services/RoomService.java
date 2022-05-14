package it.sevenbits.quiz.core.services;

import it.sevenbits.quiz.core.exceptions.QuizErrorCode;
import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.model.Room;
import it.sevenbits.quiz.core.repositories.interfaces.IRoomRepository;
import it.sevenbits.quiz.core.services.interfaces.IRoomService;
import it.sevenbits.web.dto.responses.GetRoomResponse;
import it.sevenbits.web.dto.responses.GetRoomsResponse;
import org.springframework.stereotype.Service;

/**
 * room service class
 */
@Service
public class RoomService implements IRoomService {
  private final IRoomRepository roomRepository;

  /**
   * constructor for RoomService
   * @param roomRepository - room repo from bean
   */
  public RoomService(final IRoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  @Override
  public GetRoomsResponse getAllRooms() {
    return new GetRoomsResponse(roomRepository.getAllRooms());
  }

  @Override
  public GetRoomResponse createRoom(final String roomId, final String roomName) throws QuizException {
    if (roomId == null || roomName == null || roomId.equals("") || roomName.equals("")) {
      throw new QuizException(QuizErrorCode.WRONG_INPUTS);
    }
    roomRepository.createRoom(roomId, roomName);
    return new GetRoomResponse(roomId, roomName, roomRepository.getRoomById(roomId).getPlayers());
  }

  @Override
  public GetRoomResponse getRoomById(final String roomId) {
    Room room = roomRepository.getRoomById(roomId);
    if (room == null) {
      room = new Room("Undefined", "Undefined");
    }
    return new GetRoomResponse(room.getRoomId(), room.getRoomName(), room.getPlayers());
  }

  @Override
  public GetRoomResponse joinRoom(final String roomId, final String playerId) throws QuizException {
    if (!checkRoomIsInRepo(roomId)) {
      throw new QuizException(QuizErrorCode.ROOM_NOT_FOUND);
    }
    roomRepository.addPlayer(roomId, playerId);
    return new GetRoomResponse(roomId,
            roomRepository.getRoomById(roomId).getRoomName(),
            roomRepository.getRoomById(roomId).getPlayers());
  }

  private boolean checkRoomIsInRepo(final String roomId) {
    return roomRepository.checkRoomIsInRepository(roomId);
  }


}