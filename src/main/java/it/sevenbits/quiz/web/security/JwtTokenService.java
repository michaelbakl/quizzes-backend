package it.sevenbits.quiz.web.security;


import it.sevenbits.quiz.core.model.User;

/**
 * jwt token service class
 */
public interface JwtTokenService {

  /**
   * Creates new Token for user.
   * @param user contains User to be represented as token
   * @return signed token
   */
  String createToken(User user);

  /**
   * Parses the token
   * @param token the token string to parse
   * @return authenticated data
   */
  UserCredentials parseToken(String token);

}

