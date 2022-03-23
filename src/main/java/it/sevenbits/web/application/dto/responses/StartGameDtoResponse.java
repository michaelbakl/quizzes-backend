package it.sevenbits.web.application.dto.responses;

/**
 * start game dto response
 */
public class StartGameDtoResponse {
    private String questionId;

    /**
     * constructor
     * @param questionId - String
     */
    public StartGameDtoResponse(final String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(final String questionId) {
        this.questionId = questionId;
    }
}
