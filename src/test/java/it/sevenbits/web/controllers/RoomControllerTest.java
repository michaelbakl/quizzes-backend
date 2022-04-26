package it.sevenbits.web.controllers;

import it.sevenbits.quiz.core.services.RoomService;
import it.sevenbits.quiz.core.services.interfaces.IRoomService;
import it.sevenbits.web.dto.requests.CreateRoomRequest;
import it.sevenbits.web.dto.requests.JoinRoomRequest;
import it.sevenbits.web.dto.responses.GetRoomInfoResponse;
import it.sevenbits.web.dto.responses.GetRoomResponse;
import it.sevenbits.web.dto.responses.GetRoomsResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RoomControllerTest {
  private IRoomService mockService;
  private RoomController roomController;

  @Before
  public void setup() {
    mockService = mock(RoomService.class);
    roomController = new RoomController(mockService);
  }

  @Test
  public void getAllRooms() {
    GetRoomsResponse mockRooms = mock(GetRoomsResponse.class);
    GetRoomInfoResponse mockRoomInfo = mock(GetRoomInfoResponse.class);
    GetRoomInfoResponse[] res = new GetRoomInfoResponse[1];
    res[0] = mockRoomInfo;
    when(mockService.getAllRooms()).thenReturn(mockRooms);
    when(mockRooms.getResponse()).thenReturn(res);

    ResponseEntity<GetRoomInfoResponse[]> answer = roomController.getAllRooms();

    verify(mockService, times(1)).getAllRooms();
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(res, answer.getBody());

  }

  @Test
  public void createRoom() {
    GetRoomResponse mockResponse = mock(GetRoomResponse.class);
    CreateRoomRequest request = new CreateRoomRequest("1", "1");
    when(mockService.createRoom(anyString(), anyString())).thenReturn(mockResponse);

    ResponseEntity<GetRoomResponse> answer = roomController.createRoom(request);
    verify(mockService, times(1)).createRoom(anyString(), anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }

  @Test
  public void getRoom() {
    GetRoomResponse mockResponse = mock(GetRoomResponse.class);

    when(mockService.getRoomById(anyString())).thenReturn(mockResponse);

    ResponseEntity<GetRoomResponse> answer = roomController.getRoom("1");
    verify(mockService, times(1)).getRoomById(anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());

  }

  @Test
  public void joinRoom() {
    GetRoomResponse mockResponse = mock(GetRoomResponse.class);
    JoinRoomRequest request = new JoinRoomRequest("1");

    when(mockService.joinRoom(anyString(), anyString())).thenReturn(mockResponse);
    ResponseEntity<GetRoomResponse> answer = roomController.joinRoom(request, "9");
    verify(mockService, times(1)).joinRoom(anyString(), anyString());
    assertEquals(HttpStatus.OK, answer.getStatusCode());
    assertSame(mockResponse, answer.getBody());
  }
}