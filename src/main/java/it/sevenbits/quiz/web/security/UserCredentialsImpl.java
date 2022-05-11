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

  @JsonProperty("roles")
  private final Set<String> roles;

  /**
   * constructor
   * @param userId - username of user
   * @param roles - user`s roles
   */
  @SuppressWarnings("checkstyle:RedundantModifier")
  @JsonCreator
  public UserCredentialsImpl(final String userId, final Collection<String> roles) {
    this.userId = userId;
    this.roles = Collections.unmodifiableSet(new LinkedHashSet<>(roles));
  }

  public String getUserId() {
    return userId;
  }

  public Set<String> getRoles() {
    return roles;
  }

}
