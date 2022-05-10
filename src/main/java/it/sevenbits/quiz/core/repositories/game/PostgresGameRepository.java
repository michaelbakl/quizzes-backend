package it.sevenbits.quiz.core.repositories.game;

import it.sevenbits.quiz.core.model.Game;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

/**
 * implementation of game repository using postgres database
 */
public class PostgresGameRepository implements IGameRepository {

  private final JdbcOperations jdbcOperations;

  /**
   * constructor
   * @param jdbcOperations - instrument for database
   */
  public PostgresGameRepository(final JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  public Game getGame(final String roomId) {
    return jdbcOperations.queryForObject("SELECT * FROM game WHERE roomId = ?", (resultSet, i) ->
            new Game(resultSet.getInt("score"),
                    resultSet.getInt("questionsAmount"),
                    getQuestionIds(roomId),
                    getPositionNumberInList(getQuestionIds(roomId), resultSet.getString("currentQuestionId")),
                    resultSet.getString("status")
            ), roomId);
  }

  @Override
  public void createGame(final String roomId, final Game game) {
    jdbcOperations.update("INSERT INTO game (roomid, score, questionsamount, currentquestionid, status) VALUES (?, ?, ?, ?, ?)",
            roomId, game.getScore(), game.getQuestionsAmount(), game.getCurrentQuestionId(), game.getStatus());
    List<String> questionsIds = game.getQuestionsIds();
    for (String questionId : questionsIds) {
      jdbcOperations.update("INSERT INTO questionstogame (roomid, questionid) VALUES (?, ?)",
              roomId, questionId);
    }
  }

  @Override
  public void updateGameScore(final int score, final String roomId) {
    jdbcOperations.update("UPDATE game SET score = ? WHERE roomid = ?",
            score, roomId);
  }

  @Override
  public int getGameScore(final String roomId) {
    try {
      return jdbcOperations.queryForObject("SELECT score FROM game WHERE roomId = ?",
              (resultSet, i) -> resultSet.getInt("score"),
              roomId);
    } catch (NullPointerException e) {
      return 0;
    }
  }

  @Override
  public boolean checkGameIsInProgress(final String roomId) {
    try {
      int checker = jdbcOperations.queryForObject("SELECT COUNT(roomid) AS counter FROM game WHERE roomid =?",
              (resultSet, i) -> resultSet.getInt("counter"), roomId);
      return checker == 1;
    } catch (NullPointerException e) {
      return false;
    }
  }

  @Override
  public String getIdOfCurrentQuestion(final String roomId) {
    return jdbcOperations.queryForObject("SELECT currentquestionid FROM game WHERE roomId = ?",
            (resultSet, i) -> resultSet.getString("currentquestionid"),
            roomId);
  }

  @Override
  public String getNextQuestionId(final String roomId) {
    Game game = getGame(roomId);
    return game.getNextId();
  }

  @Override
  public void updateGame(final String roomId, final Game game) {
    List<String> questionsIds = game.getQuestionsIds();
    jdbcOperations.update("UPDATE game SET score = ?, questionsamount = ?, currentquestionid = ?, status = ? WHERE roomid = ?",
            game.getScore(), game.getQuestionsAmount(), game.getCurrentIdPos(), game.getStatus(), roomId);
    for (String questionId: questionsIds) {
      jdbcOperations.update("UPDATE questionstogame SET questionid = ? WHERE roomid = ?",
              questionId, roomId);
    }
  }

  private List<String> getQuestionIds(final String roomId) {
    List<String> questionsIds = jdbcOperations.query("SELECT questionId FROM questionstogame WHERE roomid = ? ORDER BY questionid",
            (resultSet, i) -> resultSet.getString("questionId"), roomId);
    return questionsIds;
  }

  private int getPositionNumberInList(final List<String> list, final String id) {
    int i;
    for (i = 0; i < list.size(); i++) {
      if (list.get(i).equals(id)) {
        return i;
      }
    }
    return 0;
  }

}
