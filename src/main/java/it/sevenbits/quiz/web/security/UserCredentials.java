package it.sevenbits.quiz.web.security;

import java.util.Set;

/**
 * user credentials class
 */
public interface UserCredentials {

  /**
   * returns id of user
   * @return userId
   */
  String getUserId();

  /**
   * returns user email
   * @return email
   */
  String getEmail();

  /**
   * returns roles of user
   * @return set of roles
   */
  Set<String> getRoles();

}

