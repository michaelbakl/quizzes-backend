package it.sevenbits.quiz.core.model;

import java.util.List;

/**
 * model question class
 */
public class Question {
    private final String id;
    private String content;
    private List<Answer> answers;
    private Answer correctAnswer;

    /**
     * constructor
     *
     * @param id - String
     * @param content - String
     * @param answers - List
     * @param correctAnswer - Answer
     */
    public Question(final String id,
                    final String content,
                    final List<Answer> answers,
                    final Answer correctAnswer) {
        this.id = id;
        this.content = content;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(final List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(final Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}