package it.sevenbits.quiz.core.repositories.room;

import it.sevenbits.quiz.core.model.Player;
import it.sevenbits.quiz.core.model.Room;
import it.sevenbits.quiz.core.repositories.room.RoomRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomRepositoryTest {
  RoomRepository repository;

  @Before
  public void setup() {
    repository = new RoomRepository();
  }

  @Test
  public void createRoom() {
    String roomId = "roomId";
    String roomName = "roomName";
    String ownerId = "ownerId";
    repository.createRoom(roomId, roomName, ownerId);
    assertNotNull(repository.getRoomById(roomId));
    assertEquals(roomId, repository.getRoomById(roomId).getRoomId());
    assertEquals(roomName, repository.getRoomById(roomId).getRoomName());
  }

  @Test
  public void getAllRooms() {
    repository.createRoom("1", "1", "owner1");
    repository.createRoom("2", "2", "owner2");
    List<Room> actualList = repository.getAllRooms();
    assertEquals(2, actualList.size());
    assertEquals("1", actualList.get(0).getRoomId());
    assertEquals("1", actualList.get(0).getRoomName());
    assertEquals("2", actualList.get(1).getRoomId());
    assertEquals("2", actualList.get(1).getRoomName());

  }

  @Test
  public void getRoomById() {
    repository.createRoom("1", "1", "owner1");
    Room actualRoom = repository.getRoomById("1");
    assertEquals("1", actualRoom.getRoomId());
    assertEquals("1", actualRoom.getRoomName());
  }

  @Test
  public void addPlayer() {
    repository.createRoom("1", "1", "owner1");
    repository.addPlayer("1", "Pl1");
    Player actual = repository.getRoomById("1").getPlayerById("Pl1");
    assertEquals("Pl1", actual.getPlayerId());
  }

  @Test
  public void updatePlayerScore() {
    repository.createRoom("1", "1", "owner1");
    repository.addPlayer("1", "Pl1");
    repository.updatePlayerScore("1", "Pl1", 10);
    Player actual = repository.getRoomById("1").getPlayerById("Pl1");
    assertEquals("Pl1", actual.getPlayerId());
    assertEquals(10, actual.getPoints());
  }
}