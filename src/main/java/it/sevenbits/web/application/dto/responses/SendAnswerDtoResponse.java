package it.sevenbits.web.application.dto.responses;

public class SendAnswerDtoResponse {
    public int result;
    public int score;
    public int nextQuestionId;

    public SendAnswerDtoResponse(int result, int score, int nextQuestionId) {
        this.result = result;
        this.score = score;
        this.nextQuestionId = nextQuestionId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(int nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }
}
