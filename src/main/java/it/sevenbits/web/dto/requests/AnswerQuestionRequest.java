package it.sevenbits.web.dto.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * answer question request class
 */
public class AnswerQuestionRequest {
    private final String id;

    /**
     * constructor
     * @param id - String
     */
    @JsonCreator
    public AnswerQuestionRequest(@JsonProperty("answerId") final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
