package it.sevenbits.web.application.dto.responses;

/**
 * send answer dto response
 */
public class SendAnswerDtoResponse {
    private int result;
    private int score;
    private final String nextQuestionId;

    /**
     * constructor
     * @param result - int
     * @param score - int
     * @param nextQuestionId - String
     */
    public SendAnswerDtoResponse(final int result, final int score, final String nextQuestionId) {
        this.result = result;
        this.score = score;
        this.nextQuestionId = nextQuestionId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(final int result) {
        this.result = result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    public String getNextQuestionId() {
        return nextQuestionId;
    }

}
