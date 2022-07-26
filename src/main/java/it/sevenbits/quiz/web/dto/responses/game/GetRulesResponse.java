package it.sevenbits.quiz.web.dto.responses.game;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * get rules response class
 */
public class GetRulesResponse {
  @JsonProperty
  private final String rules;

  /**
   * constructor
   * @param rules - game rules
   */
  public GetRulesResponse(@JsonProperty("rules") final String rules) {
    this.rules = rules;
  }

  public String getRules() {
    return rules;
  }
}
