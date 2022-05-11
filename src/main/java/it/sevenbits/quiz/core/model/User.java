package it.sevenbits.quiz.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * user class model
 */
public class User {

  @JsonProperty("userId")
  private String userId;

  @JsonProperty("email")
  private String email;

  @JsonProperty("roles")
  private List<String> roles;

  @JsonIgnore
  private String password;


  /**
   * constructor without password
   * @param userId - user id
   * @param email - email
   * @param roles - user roles
   */
  public User(final String userId, final String email, final List<String> roles) {
    this.userId = userId;
    this.email = email;
    this.roles = roles;
  }

  /**
   * constructor with password
   * @param userId - user id
   * @param email - email
   * @param roles - user roles
   * @param password - user password
   */
  public User(final String userId,
              final String email,
              final List<String> roles,
              final String password) {
    this.userId = userId;
    this.email = email;
    this.roles = roles;
    this.password = password;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(final String userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(final List<String> roles) {
    this.roles = roles;
  }
}
