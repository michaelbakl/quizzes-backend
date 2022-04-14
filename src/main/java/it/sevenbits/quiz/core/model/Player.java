package it.sevenbits.quiz.core.model;

/**
 * player model class
 */
public class Player {
  private String playerId;

  /**
   * constructor
   *
   * @param playerId - String
   */
  public Player(final String playerId) {
    this.playerId = playerId;
  }

  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(final String playerId) {
    this.playerId = playerId;
  }
}
