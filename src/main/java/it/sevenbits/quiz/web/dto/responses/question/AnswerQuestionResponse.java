package it.sevenbits.quiz.web.dto.responses.question;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * send answer dto response
 */
public class AnswerQuestionResponse {
    @JsonProperty
    private final String correctAnswerId;
    @JsonProperty
    private final String questionId;
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
                                  @JsonProperty("questionId") final String nextQuestionId,
                                  @JsonProperty("totalScore") final int totalScore,
                                  @JsonProperty("questionScore") final int questionScore) {
        this.correctAnswerId = correctAnswer;
        this.questionScore = questionScore;
        this.totalScore = totalScore;
        this.questionId = nextQuestionId;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getCorrectAnswerId() {
        return correctAnswerId;
    }
}
