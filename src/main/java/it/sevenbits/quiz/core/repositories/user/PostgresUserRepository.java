package it.sevenbits.quiz.core.repositories.user;

import it.sevenbits.quiz.core.model.User;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

/**
 * implementation of IUserRepository
 */
public class PostgresUserRepository implements IUserRepository {

  private final JdbcOperations jdbcOperations;
  @SuppressWarnings("checkstyle:MemberName")
  private final String ROLE = "role";
  @SuppressWarnings("checkstyle:MemberName")
  private final String USERNAME = "username";
  @SuppressWarnings("checkstyle:MemberName")
  private final String PASSWORD = "password";


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
            (resultSet, i) -> new User(resultSet.getString("email"),
                    resultSet.getString("password"),
                    getRoles(resultSet.getString("email"))), email);
  }

  @Override
  public List<User> findAll() {
    return jdbcOperations.query("SELECT * FROM users", (resultSet, i) ->
            new User(resultSet.getString("email"),
                    resultSet.getString("password"),
                    getRoles(resultSet.getString("email"))));
  }

  @Override
  public void createUser(final String email, final String password) {
    jdbcOperations.update("INSERT INTO users VALUES (?, ?)", email, password);
    jdbcOperations.update("INSERT INTO userroles VALUES (?, ?)", email, "USER");
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

  private List<String> getRoles(final String email) {
    return jdbcOperations.query("SELECT * FROM userroles WHERE email = ?",
            (resultSet, i) -> resultSet.getString("role"), email);
  }
}
