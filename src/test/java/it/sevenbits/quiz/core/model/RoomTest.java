package it.sevenbits.quiz.core.model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class RoomTest extends TestCase {

  public void testGettersAndSetters() {
    List<Player> playerList = new ArrayList<>();
    Player mockPlayer = mock(Player.class);
    playerList.add(mockPlayer);
    playerList.add(mockPlayer);
    playerList.add(mockPlayer);
    Player expectedPlayer = new Player("1");
    Room room = new Room("1", "name", "1", playerList);

    assertEquals("1", room.getRoomId());
    assertEquals("name", room.getRoomName());
    assertEquals("1", room.getOwnerId());
    assertEquals(playerList, room.getPlayers());

    playerList.add(expectedPlayer);

    room.setRoomId("2");
    room.setRoomName("2");
    room.setOwnerId("2");
    room.setPlayers(playerList);

    assertEquals("2", room.getRoomId());
    assertEquals("2", room.getRoomName());
    assertEquals("2", room.getOwnerId());
    assertEquals(playerList, room.getPlayers());

  }
}