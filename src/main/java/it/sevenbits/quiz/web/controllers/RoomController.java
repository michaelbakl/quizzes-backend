package it.sevenbits.quiz.web.controllers;

import it.sevenbits.quiz.core.exceptions.QuizErrorCode;
import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.services.interfaces.IRoomService;
import it.sevenbits.quiz.web.dto.responses.room.GetRoomInfoResponse;
import it.sevenbits.quiz.web.dto.responses.room.GetRoomResponse;
import it.sevenbits.quiz.web.dto.responses.room.GetRoomsResponse;
import it.sevenbits.quiz.web.dto.requests.room.CreateRoomRequest;
import it.sevenbits.quiz.web.security.AuthRoleRequired;
import it.sevenbits.quiz.web.security.UserCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

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
  @AuthRoleRequired("USER")
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
   * @param userCredentials - user info
   * @return GetRoomsResponse
   */
  @RequestMapping(method = RequestMethod.POST)
  @AuthRoleRequired("USER")
  ResponseEntity<GetRoomResponse> createRoom(
          @RequestBody final CreateRoomRequest request,
          final UserCredentials userCredentials
  ) {
    try {
      GetRoomResponse response = roomService.createRoom(
              UUID.randomUUID().toString(),
              request.getRoomName(),
              userCredentials.getUserId()
      );
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
  @AuthRoleRequired("USER")
  ResponseEntity<GetRoomResponse> getRoom(@PathVariable("id") final String roomId) {
    try {
      if (!isUUID(roomId)) {
        throw new QuizException(QuizErrorCode.WRONG_INPUTS);
      }
      GetRoomResponse response = roomService.getRoomById(roomId);
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (QuizException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   *
   * @param roomId - String
   * @param userCredentials - user info
   * @return GetRoomResponse
   */
  @RequestMapping(value = "/{id}/join", method = RequestMethod.POST)
  @AuthRoleRequired("USER")
  ResponseEntity<GetRoomResponse> joinRoom(
          @PathVariable("id") final String roomId,
          final UserCredentials userCredentials
  ) {
    try {
      if (!isUUID(roomId)) {
        throw new QuizException(QuizErrorCode.WRONG_INPUTS);
      }
      GetRoomResponse response = roomService.joinRoom(roomId, userCredentials.getUserId());
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (QuizException e) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }

  /**
   *
   * @param roomId - String
   * @param userCredentials - user info
   * @return GetRoomResponse
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @AuthRoleRequired("USER")
  ResponseEntity<String> deleteRoom(
          @PathVariable("id") final String roomId,
          final UserCredentials userCredentials
  ) {
    try {
      if (!isUUID(roomId)) {
        throw new QuizException(QuizErrorCode.WRONG_INPUTS);
      }
      roomService.deleteRoom(roomId);
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Group deleted");
    } catch (QuizException e) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }

  private boolean isUUID(final String testUUID) {
    if (testUUID == null) {
      return false;
    }
    return java.util.regex.Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
            .matcher(testUUID).find();
  }

}
