package it.sevenbits.quiz.web.controllers;


import it.sevenbits.quiz.core.services.game.IGameService;
import it.sevenbits.quiz.web.dto.responses.game.GetRulesResponse;
import it.sevenbits.quiz.web.security.AuthRoleRequired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * rules controller class
 */
@Controller
@RequestMapping("/rules")
public class RulesController {
  private final IGameService gameService;

  /**
   * constructor
   * @param gameService - IGameService
   */
  public RulesController(final IGameService gameService) {
    this.gameService = gameService;
  }

  /**
   * get rules method
   *
   * @return GetRulesResponse
   */
  @RequestMapping(method = RequestMethod.GET)
  @AuthRoleRequired("USER")
  ResponseEntity<GetRulesResponse> getAllRooms() {
    try {
      GetRulesResponse response = gameService.getRules();
      return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}
