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

  @JsonProperty("username")
  private final String username;

  @JsonProperty("roles")
  private final Set<String> roles;

  /**
   * constructor
   * @param username - username of user
   * @param roles - user`s roles
   */
  @SuppressWarnings("checkstyle:RedundantModifier")
  @JsonCreator
  public UserCredentialsImpl(final String username, final Collection<String> roles) {
    this.username = username;
    this.roles = Collections.unmodifiableSet(new LinkedHashSet<>(roles));
  }

  public String getUsername() {
    return username;
  }

  public Set<String> getRoles() {
    return roles;
  }

}
