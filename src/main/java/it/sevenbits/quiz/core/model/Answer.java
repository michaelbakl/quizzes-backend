package it.sevenbits.quiz.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * model answer
 */
public class Answer {
    @JsonProperty
    private String answerId;
    @JsonProperty
    private String answerText;
    private int points;

    /**
     * constructor
     *
     * @param id - String
     * @param content - String
     * @param points - int
     */
    public Answer(final String id,
                  final String content,
                  final int points) {
        this.answerId = id;
        this.answerText = content;
        this.points = points;
    }

    public String getAnswerId() {
        return answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public int getPoints() {
        return points;
    }

    public void setAnswerId(final String answerId) {
        this.answerId = answerId;
    }

    public void setAnswerText(final String answerText) {
        this.answerText = answerText;
    }

    public void setPoints(final int points) {
        this.points = points;
    }
}

