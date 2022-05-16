package it.sevenbits.quiz.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * start game dto response
 */
public class StartGameDtoResponse {
    private final String questionId;

    /**
     * constructor
     * @param questionId - String
     */
    public StartGameDtoResponse(@JsonProperty("questionId") final String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return questionId;
    }
}
