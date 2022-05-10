package it.sevenbits.quiz.web.security;

import java.util.Set;

/**
 * user credentials class
 */
public interface UserCredentials {

  /**
   * returns username of user
   * @return username
   */
  String getUsername();

  /**
   * returns roles of user
   * @return set of roles
   */
  Set<String> getRoles();

}

