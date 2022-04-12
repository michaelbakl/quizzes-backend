package it.sevenbits.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * answer question request class
 */
public class AnswerQuestionRequest {
    @JsonProperty
    private final String answerId;

    /**
     * constructor
     * @param id - String
     */
    @JsonCreator
    public AnswerQuestionRequest(@JsonProperty("answerId") final String id) {
        this.answerId = id;
    }

    public String getAnswerId() {
        return answerId;
    }
}
