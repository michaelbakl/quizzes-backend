package it.sevenbits.quiz.web.controllers;

import it.sevenbits.quiz.web.security.AuthRoleRequired;
import it.sevenbits.quiz.web.security.UserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller to display the current user.
 */
@Controller
@RequestMapping("/whoami")
public class WhoamiController {

  /**
   * method for getting whoami info
   * @param userCredentials - user info
   * @return response body for who am i
   */
  @GetMapping
  @ResponseBody
  @AuthRoleRequired("USER")
  public ResponseEntity<UserCredentials> get(
          final UserCredentials userCredentials
  ) {
    return ResponseEntity.ok(userCredentials);
  }


}
