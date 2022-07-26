package it.sevenbits.quiz.core.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * bcrypt password encoder class
 */
public class BCryptPasswordEncoder implements PasswordEncoder {

  /**
   *
   * @param plainPassword - input password
   * @param hashedPassword - hashed password
   * @return true if
   */
  public boolean matches(final String plainPassword, final String hashedPassword) {
    return BCrypt.checkpw(plainPassword, hashedPassword);
  }

}

