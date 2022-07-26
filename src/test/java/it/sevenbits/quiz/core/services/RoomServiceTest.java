package it.sevenbits.quiz.core.services;

import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.model.Player;
import it.sevenbits.quiz.core.model.Room;
import it.sevenbits.quiz.core.repositories.room.RoomRepository;
import it.sevenbits.quiz.core.repositories.room.IRoomRepository;
import it.sevenbits.quiz.core.services.room.RoomService;
import it.sevenbits.quiz.web.dto.responses.room.GetRoomResponse;
import it.sevenbits.quiz.web.dto.responses.room.GetRoomsResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomServiceTest {
  IRoomRepository mockRoomRepository;
  RoomService roomService;

  @Before
  public void setup() {
    mockRoomRepository = mock(RoomRepository.class);
    roomService = new RoomService(mockRoomRepository);
  }

  @Test
  public void getAllRooms() {
    List<Room> mockList = mock(List.class);
    when(mockRoomRepository.getAllRooms()).thenReturn(mockList);

    GetRoomsResponse response = roomService.getAllRooms();

    assertNotNull(response);
  }

  @Test
  public void createRoom() throws QuizException {
    Room mockRoom = mock(Room.class);
    List<Player> mockList = mock(List.class);
    when(mockRoomRepository.getRoomById(anyString())).thenReturn(mockRoom);
    when(mockRoom.getPlayers()).thenReturn(mockList);
    GetRoomResponse response = roomService.createRoom("1", "name", "owner");

    assertEquals("1", response.getRoomId());
    assertEquals("name", response.getRoomName());
    assertEquals("owner", response.getOwnerId());
    assertEquals(mockList, response.getPlayers());
  }

  @Test
  public void getRoomById() {
    Room mockRoom = mock(Room.class);
    List<Player> mockList = mock(List.class);
    when(mockRoomRepository.getRoomById(anyString())).thenReturn(mockRoom);
    when(mockRoom.getRoomId()).thenReturn("1");
    when(mockRoom.getRoomName()).thenReturn("name");
    when(mockRoom.getPlayers()).thenReturn(mockList);

    GetRoomResponse response = roomService.getRoomById("1");

    assertEquals("1", response.getRoomId());
    assertEquals("name", response.getRoomName());
    assertEquals(mockList, response.getPlayers());
  }

  @Test
  public void joinRoom() throws QuizException {
    Room mockRoom = mock(Room.class);
    List<Player> mockList = mock(List.class);
    when(mockRoomRepository.getRoomById(anyString())).thenReturn(mockRoom);
    when(mockRoom.getRoomName()).thenReturn("name");
    when(mockRoom.getPlayers()).thenReturn(mockList);
    when(mockRoomRepository.checkRoomIsInRepository(anyString())).thenReturn(true);

    GetRoomResponse response = roomService.joinRoom("1", "pl1");

    assertEquals("1", response.getRoomId());
    assertEquals("name", response.getRoomName());
    assertEquals(mockList, response.getPlayers());

  }
}