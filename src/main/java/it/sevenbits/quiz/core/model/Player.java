package it.sevenbits.quiz.core.model;

/**
 * player model class
 */
public class Player {
  private String playerId;

  private int points;

  /**
   * constructor
   *
   * @param playerId - String
   */
  public Player(final String playerId) {
    this.playerId = playerId;
    points = 0;
  }

  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(final String playerId) {
    this.playerId = playerId;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(final int points) {
    this.points = points;
  }

  /**
   * adds value to points
   * @param value - int
   */
  public void updatePoints(final int value) {
    this.points += value;
  }
}
