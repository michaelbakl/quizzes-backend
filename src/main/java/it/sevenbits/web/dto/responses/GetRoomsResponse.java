package it.sevenbits.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.quiz.core.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * get rooms response class
 */
public class GetRoomsResponse {
  @JsonProperty
  private final List<GetRoomInfoResponse> list;

  /**
   * constructor
   * @param list - List
   */
  public GetRoomsResponse(final List<Room> list) {
    this.list = new ArrayList<>();
    for (Room room: list) {
      this.list.add(new GetRoomInfoResponse(room.getRoomId(), room.getRoomName()));
    }
  }

  public GetRoomInfoResponse[] getList() {
    return list.toArray(new GetRoomInfoResponse[0]);
  }
}
