package it.sevenbits.web.application.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * model answer
 */
public class Answer {
    private String id;
    private String content;
    private int points;

    /**
     * constructor
     *
     * @param id - String
     * @param content - String
     * @param points - int
     */
    @JsonCreator
    public Answer(@JsonProperty("id") final String id,
                  @JsonProperty("content") final String content,
                  @JsonProperty("points")final int points) {
        this.id = id;
        this.content = content;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getPoints() {
        return points;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setPoints(final int points) {
        this.points = points;
    }
}

