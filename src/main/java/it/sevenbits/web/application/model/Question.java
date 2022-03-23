package it.sevenbits.web.application.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Question {
    private String id;
    private String content;
    List<Answer> answers;
    private Answer correctAnswer;

    @JsonCreator
    public Question(@JsonProperty("id") String id,
                    @JsonProperty("content")String content,
                    @JsonProperty("answers") List<Answer> answers,
                    @JsonProperty("correctAnswer")Answer correctAnswer)
    {
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

    public void setContent(String content) {
        this.content = content;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
