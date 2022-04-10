package it.sevenbits.quiz.core.model;

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
    public Answer(final String id,
                  final String content,
                  final int points) {
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

