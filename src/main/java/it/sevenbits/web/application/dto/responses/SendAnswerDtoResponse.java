package it.sevenbits.web.application.dto.responses;

/**
 * send answer dto response
 */
public class SendAnswerDtoResponse {
    private String correctAnswerId;
    private int questionScore;
    private int totalScore;
    private final String nextQuestionId;

    /**
     * constructor
     * @param questionScore - int
     * @param totalScore - int
     * @param nextQuestionId - String
     */
    public SendAnswerDtoResponse(final int questionScore, final int totalScore, final String nextQuestionId) {
        this.questionScore = questionScore;
        this.totalScore = totalScore;
        this.nextQuestionId = nextQuestionId;
    }

    public int getResult() {
        return questionScore;
    }

    public void setResult(final int result) {
        this.questionScore = result;
    }

    public int getScore() {
        return totalScore;
    }

    public void setScore(final int score) {
        this.totalScore = score;
    }

    public String getNextQuestionId() {
        return nextQuestionId;
    }

}
