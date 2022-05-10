package it.sevenbits.quiz.web.controllers;


import it.sevenbits.quiz.core.exceptions.SignUpException;
import it.sevenbits.quiz.core.model.User;
import it.sevenbits.quiz.core.services.login.LoginService;
import it.sevenbits.quiz.web.dto.requests.user.SignInRequest;
import it.sevenbits.quiz.web.dto.requests.user.SignUpRequest;
import it.sevenbits.quiz.web.dto.responses.user.SignInResponse;
import it.sevenbits.quiz.web.model.Login;
import it.sevenbits.quiz.web.model.Token;
import it.sevenbits.quiz.web.security.JwtTokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Performs login action.
 */
@Controller
public class BodyLoginController {

  private final LoginService loginService;
  private final JwtTokenService tokenService;

  /**
   * constructor
   * @param loginService - login service
   * @param tokenService - token service
   */
  public BodyLoginController(final LoginService loginService, final JwtTokenService tokenService) {
    this.loginService = loginService;
    this.tokenService = tokenService;
  }

//  @RequestMapping(method = RequestMethod.POST)
//  @ResponseBody
//  public Token create(@RequestBody final Login login) {
//    System.out.println("HELLO");
//    User user = loginService.login(login);
//    String token = tokenService.createToken(user);
//    return new Token(token);
//  }

  /**
   * logins user on server
   * @param login - sign in request
   * @return token
   */
  @RequestMapping(value = "/signin", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<SignInResponse> signIn(@RequestBody final SignInRequest login) {
    User user = loginService.login(new Login(login.getEmail(), login.getPassword()));
    String token = tokenService.createToken(user);
    Token res = new Token(token);
    return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(new SignInResponse(token));
  }

  /**
   * create user on server
   * @param login - sign up request
   * @return String
   */
  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<String> signUp(@RequestBody final SignUpRequest login) {
    try {
      loginService.create(new Login(login.getEmail(), login.getPassword()));
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Signed up successfully!");
    } catch (SignUpException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }



}
