package it.sevenbits.web.application.dto.responses;

public class StartGameDtoResponse {
    public String questionId;

    public StartGameDtoResponse(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
