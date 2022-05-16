package it.sevenbits.quiz.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * answer question request class
 */
public class AnswerQuestionRequest {
    @JsonProperty
    private final String playerId;
    @JsonProperty
    private final String answerId;

    /**
     *
     * @param playerId - String
     * @param answerId - String
     */
    @JsonCreator
    public AnswerQuestionRequest(@JsonProperty("playerId") final String playerId,
                                 @JsonProperty("answerId") final String answerId) {
        this.playerId = playerId;
        this.answerId = answerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getAnswerId() {
        return answerId;
    }

}
