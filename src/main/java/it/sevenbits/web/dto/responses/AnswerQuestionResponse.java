package it.sevenbits.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * send answer dto response
 */
public class AnswerQuestionResponse {
    private final String correctAnswerId;
    private final String nextQuestionId;
    private final int totalScore;
    private final int questionScore;

    /**
     *
     * @param correctAnswer - String
     * @param nextQuestionId - String
     * @param totalScore - int
     * @param questionScore - int
     */
    public AnswerQuestionResponse(@JsonProperty("correctAnswer") final String correctAnswer,
                                  @JsonProperty("nextQuestionId") final String nextQuestionId,
                                  @JsonProperty("totalScore") final int totalScore,
                                  @JsonProperty("questionScore") final int questionScore) {
        this.correctAnswerId = correctAnswer;
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
