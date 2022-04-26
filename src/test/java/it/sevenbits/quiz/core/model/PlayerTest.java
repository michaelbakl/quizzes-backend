package it.sevenbits.quiz.core.model;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

  public void testGettersAndSetters() {
    Player player = new Player("playerId");
    assertEquals("playerId", player.getPlayerId());
    assertEquals(0, player.getPoints());
    player.setPoints(10);
    assertEquals(10, player.getPoints());
    player.updatePoints(2);
    assertEquals(12, player.getPoints());
  }
}