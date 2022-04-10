package it.sevenbits.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * send answer dto response
 */
public class AnswerQuestionResponse {
    private final String correctAnswerId;
    private final int questionScore;
    private final int totalScore;
    private final String nextQuestionId;

    /**
     *
     * @param correctAnswerId - String
     * @param nextQuestionId - String
     * @param totalScore - int
     * @param questionScore - int
     */
    public AnswerQuestionResponse(@JsonProperty("correctAnswerId") final String correctAnswerId,
                                  @JsonProperty("totalScore") final int totalScore,
                                  @JsonProperty("nextQuestionId") final String nextQuestionId,
                                  @JsonProperty("questionScore") final int questionScore) {
        this.correctAnswerId = correctAnswerId;
        this.questionScore = questionScore;
        this.totalScore = totalScore;
        this.nextQuestionId = nextQuestionId;
    }

    public int getResult() {
        return questionScore;
    }

    public int getScore() {
        return totalScore;
    }

    public String getNextQuestionId() {
        return nextQuestionId;
    }

    public String getCorrectAnswerId() {
        return correctAnswerId;
    }
}
