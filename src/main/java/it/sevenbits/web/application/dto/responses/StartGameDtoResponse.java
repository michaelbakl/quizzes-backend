package it.sevenbits.web.application.dto.responses;

public class StartGameDtoResponse {
    public int questionId;

    public StartGameDtoResponse(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
