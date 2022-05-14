package it.sevenbits.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * send answer dto response
 */
public class AnswerQuestionResponse {
    @JsonProperty
    private final String correctAnswerId;
    @JsonProperty
    private final String nextQuestionId;
    @JsonProperty
    private final int totalScore;
    @JsonProperty
    private final int questionScore;

    /**
     *
     * @param correctAnswer - String
     * @param nextQuestionId - String
     * @param totalScore - int
     * @param questionScore - int
     */
    public AnswerQuestionResponse(@JsonProperty("correctAnswerId") final String correctAnswer,
                                  @JsonProperty("nextQuestionId") final String nextQuestionId,
                                  @JsonProperty("totalScore") final int totalScore,
                                  @JsonProperty("questionScore") final int questionScore) {
        this.correctAnswerId = correctAnswer;
        this.questionScore = questionScore;
        this.totalScore = totalScore;
        this.nextQuestionId = nextQuestionId;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getNextQuestionId() {
        return nextQuestionId;
    }

    public String getCorrectAnswerId() {
        return correctAnswerId;
    }
}
