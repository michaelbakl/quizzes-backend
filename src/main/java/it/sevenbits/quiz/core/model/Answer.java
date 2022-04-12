package it.sevenbits.quiz.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * model answer
 */
public class Answer {
    @JsonProperty
    private String answerId;
    @JsonProperty
    private String text;
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
        this.text = content;
        this.points = points;
    }

    public String getAnswerId() {
        return answerId;
    }

    public String getText() {
        return text;
    }

    public int getPoints() {
        return points;
    }

    public void setAnswerId(final String answerId) {
        this.answerId = answerId;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public void setPoints(final int points) {
        this.points = points;
    }
}

