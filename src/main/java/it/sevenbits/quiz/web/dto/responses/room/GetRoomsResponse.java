package it.sevenbits.quiz.web.dto.responses.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.quiz.core.model.Room;

import java.util.List;

/**
 * get rooms response class
 */
public class GetRoomsResponse {
  @JsonProperty
  private final GetRoomInfoResponse[] response;

  /**
   * constructor
   * @param list - List
   */
  public GetRoomsResponse(final List<Room> list) {
    response = new GetRoomInfoResponse[list.size()];
    for (int i = 0; i < list.size(); i++) {
      Room room = list.get(i);
      response[i] = new GetRoomInfoResponse(room.getRoomId(), room.getRoomName(), room.getOwnerId());
    }
  }

  public GetRoomInfoResponse[] getResponse() {
    return response;
  }
}
