package it.sevenbits.quiz.core.services.login;

import it.sevenbits.quiz.core.exceptions.LoginFailedException;
import it.sevenbits.quiz.core.exceptions.SignUpException;
import it.sevenbits.quiz.core.model.User;
import it.sevenbits.quiz.core.repositories.user.IUserRepository;
import it.sevenbits.quiz.core.security.PasswordEncoder;
import it.sevenbits.quiz.web.model.Login;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * login service
 */
@Service
public class LoginService {

  private final IUserRepository users;
  private final PasswordEncoder passwordEncoder;

  /**
   * constructor
   * @param users - repository for users
   * @param passwordEncoder - password encoder
   */
  public LoginService(final IUserRepository users, final PasswordEncoder passwordEncoder) {
    this.users = users;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * login method
   * @param login login of user
   * @return User class
   */
  public User login(final Login login) {
    User user = users.findByEmail(login.getLogin());

    if (user == null) {
      throw new LoginFailedException("User '" + login.getLogin() + "' not found");
    }

    if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
      throw new LoginFailedException("Wrong password");
    }
    return new User(null, user.getEmail(), user.getRoles());
  }

  /**
   * login method
   * @param login login of use
   * @throws SignUpException - exception
   */
  public void create(final Login login) throws SignUpException {
    final int six = 6;
    final int twelve = 12;
    if (login == null
            || login.getLogin().length() < six
            || login.getPassword().length() < six
    ) {
      throw new SignUpException("Wrong inputs!");
    }
    if (users.checkLoginExists(login.getLogin())) {
      throw new SignUpException("This login is taken: " + login.getLogin());
    }
    users.createUser(login.getLogin(), BCrypt.hashpw(login.getPassword(), BCrypt.gensalt(twelve)));
  }


}
