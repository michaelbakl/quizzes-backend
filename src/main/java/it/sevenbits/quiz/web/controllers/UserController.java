package it.sevenbits.quiz.web.controllers;

import it.sevenbits.quiz.core.model.User;
import it.sevenbits.quiz.core.repositories.user.IUserRepository;
import it.sevenbits.quiz.web.security.AuthRoleRequired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * Controller to list users.
 */
@Controller
@RequestMapping("/users")
public class UserController {

  private final IUserRepository userRepository;

  /**
   * constructor
   * @param usersRepository - IUserRepository
   */
  public UserController(final IUserRepository usersRepository) {
    this.userRepository = usersRepository;
  }

  @GetMapping
  @ResponseBody
  @AuthRoleRequired("ADMIN")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userRepository.findAll());
  }

  /**
   * returns user info
   * @param email - email of user
   * @return response entity with user
   */
  @GetMapping(value = "/{id}")
  @ResponseBody
  @AuthRoleRequired("ADMIN")
  public ResponseEntity<User> getUserInfo(final @PathVariable("id") String email) {
    return Optional
            .ofNullable(userRepository.findByEmail(email))
            .map(user -> ResponseEntity.ok().body(user))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }
}

