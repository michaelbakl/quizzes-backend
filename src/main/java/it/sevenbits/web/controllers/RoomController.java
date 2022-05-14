package it.sevenbits.web.controllers;

import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.services.interfaces.IRoomService;
import it.sevenbits.web.dto.requests.CreateRoomRequest;
import it.sevenbits.web.dto.requests.JoinRoomRequest;
import it.sevenbits.web.dto.responses.GetRoomInfoResponse;
import it.sevenbits.web.dto.responses.GetRoomResponse;
import it.sevenbits.web.dto.responses.GetRoomsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * room controller class
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {

  private final IRoomService roomService;

  /**
   * constructor
   * @param roomService - IGameService
   */
  public RoomController(final IRoomService roomService) {
    this.roomService = roomService;
  }

  /**
   * get all rooms method
   *
   * @return GetRoomsResponse
   */
  @RequestMapping(method = RequestMethod.GET)
  ResponseEntity<GetRoomInfoResponse[]> getAllRooms() {
    try {
      GetRoomsResponse response = roomService.getAllRooms();
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response.getResponse());
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * creates a room from json
   *
   * @param request - CreateRoomRequest
   * @return GetRoomsResponse
   */
  @RequestMapping(method = RequestMethod.POST)
  ResponseEntity<GetRoomResponse> createRoom(@RequestBody final CreateRoomRequest request) {
    try {
      GetRoomResponse response = roomService.createRoom(request.getPlayerId(), request.getRoomName());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (QuizException e) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }

  /**
   * get room
   *
   * @param roomId - CreateRoomRequest
   * @return GetRoomsResponse
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  ResponseEntity<GetRoomResponse> getRoom(@PathVariable("id") final String roomId) {
    try {
      GetRoomResponse response = roomService.getRoomById(roomId);
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (QuizException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   *
   * @param request - JoinRoomRequest
   * @param roomId - String
   * @return GetRoomResponse
   */
  @RequestMapping(value = "/{id}/join", method = RequestMethod.POST)
  ResponseEntity<GetRoomResponse> joinRoom(@RequestBody final JoinRoomRequest request,
                                           @PathVariable("id") final String roomId) {
    try {
      GetRoomResponse response = roomService.joinRoom(roomId, request.getPlayerId());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (QuizException e) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }

}
