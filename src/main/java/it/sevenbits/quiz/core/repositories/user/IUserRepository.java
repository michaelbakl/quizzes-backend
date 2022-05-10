package it.sevenbits.quiz.core.repositories.user;

import it.sevenbits.quiz.core.model.User;

import java.util.List;

/**
 * interface for user repositories
 */
public interface IUserRepository {
  /**
   * finds user by his email
   * @param email - email of user
   * @return User
   */
  User findByEmail(String email);

  /**
   * returns all users
   * @return list of users
   */
  List<User> findAll();

  /**
   * create user with base roles
   * @param email - email
   * @param password - password
   */
  void createUser(String email, String password);

  /**
   * checks if login as email is taken
   * @param email - email
   * @return true if taken
   */
  boolean checkLoginExists(String email);

}
