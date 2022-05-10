package it.sevenbits.quiz.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * user class model
 */
public class User {

  @JsonProperty("email")
  private String email;

  @JsonProperty("roles")
  private List<String> roles;

  //@JsonIgnore
  @JsonProperty("password")
  private String password;

  /**
   * constructor
   * @param email - email of user
   * @param roles - user roles
   */
  public User(final String email, final List<String> roles) {
    this.email = email;
    this.roles = roles;
  }

  /**
   * constructor
   * @param email - email
   * @param password - password
   * @param roles - list of roles
   */
  public User(final String email, final String password, final List<String> roles) {
    this.email = email;
    this.password = password;
    this.roles = roles;
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
