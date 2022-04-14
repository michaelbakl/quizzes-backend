package it.sevenbits.quiz.core.services;

import it.sevenbits.quiz.core.model.Room;
import it.sevenbits.quiz.core.repositories.RoomRepository;
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
   * constructor
   */
  public RoomService() {
    this.roomRepository = RoomRepository.getRepository();
  }

  @Override
  public GetRoomsResponse getAllRooms() {
    return new GetRoomsResponse(roomRepository.getAllRooms());
  }

  @Override
  public GetRoomResponse createRoom(final String roomId, final String roomName) {
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
  public GetRoomResponse joinRoom(final String roomId, final String playerId) {
    roomRepository.addPlayer(roomId, playerId);
    return new GetRoomResponse(roomId,
            roomRepository.getRoomById(roomId).getRoomName(),
            roomRepository.getRoomById(roomId).getPlayers());
  }


}
