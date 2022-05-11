package it.sevenbits.quiz.web.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * user credentials implementation
 */
class UserCredentialsImpl implements UserCredentials {

  @JsonProperty("userId")
  private final String userId;

  @JsonProperty("email")
  private final String email;

  @JsonProperty("roles")
  private final Set<String> roles;

  /**
   * constructor
   * @param userId - username of user
   * @param email - email
   * @param roles - user`s roles
   */
  @JsonCreator
  UserCredentialsImpl(final String userId, final String email, final Collection<String> roles) {
    this.userId = userId;
    this.email = email;
    this.roles = Collections.unmodifiableSet(new LinkedHashSet<>(roles));
  }

  public String getUserId() {
    return userId;
  }

  public String getEmail() {
    return email;
  }

  public Set<String> getRoles() {
    return roles;
  }

}
