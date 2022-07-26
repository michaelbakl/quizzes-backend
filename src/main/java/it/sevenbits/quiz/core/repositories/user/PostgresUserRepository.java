package it.sevenbits.quiz.core.repositories.user;

import it.sevenbits.quiz.core.model.User;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

/**
 * implementation of IUserRepository
 */
public class PostgresUserRepository implements IUserRepository {

  private final JdbcOperations jdbcOperations;


  /**
   * constructor
   * @param jdbcOperations - instrument for database
   */
  public PostgresUserRepository(final JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  public User findByEmail(final String email) {
    return jdbcOperations.queryForObject("SELECT * FROM users WHERE email = ?",
            (resultSet, i) -> new User(resultSet.getString("userid"),
                    resultSet.getString("email"),
                    getRoles(resultSet.getString("userid")),
                    resultSet.getString("password")), email);
  }

  @Override
  public List<User> findAll() {
    return jdbcOperations.query("SELECT * FROM users", (resultSet, i) ->
            new User(resultSet.getString("userid"),
                    resultSet.getString("email"),
                    getRoles(resultSet.getString("userid")),
                    resultSet.getString("password")));
  }

  @Override
  public void createUser(final String userId, final String email, final String password) {
    jdbcOperations.update("INSERT INTO users VALUES (?, ?, ?)", userId, email, password);
    jdbcOperations.update("INSERT INTO userroles VALUES (?, ?)", userId, "USER");
  }

  @Override
  public boolean checkLoginExists(final String email) {
    try {
      int checker = jdbcOperations.queryForObject("SELECT COUNT(email) AS counter FROM users WHERE email =?",
              (resultSet, i) -> resultSet.getInt("counter"), email);
      return checker == 1;
    } catch (NullPointerException e) {
      return false;
    }
  }

  private List<String> getRoles(final String userId) {
    return jdbcOperations.query("SELECT * FROM userroles WHERE userid = ?",
            (resultSet, i) -> resultSet.getString("role"), userId);
  }
}
