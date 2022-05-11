package it.sevenbits.quiz.web.dto.requests.question;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * answer question request class
 */
public class AnswerQuestionRequest {
    @JsonProperty
    private final String answerId;

    /**
     *
     * @param answerId - String
     */
    @JsonCreator
    public AnswerQuestionRequest(@JsonProperty("answerId") final String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerId() {
        return answerId;
    }

}
