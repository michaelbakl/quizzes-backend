package it.sevenbits.quiz.web.dto.responses;

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
      response[i] = new GetRoomInfoResponse(list.get(i).getRoomId(), list.get(i).getRoomName());
    }
  }

  public GetRoomInfoResponse[] getResponse() {
    return response;
  }
}
