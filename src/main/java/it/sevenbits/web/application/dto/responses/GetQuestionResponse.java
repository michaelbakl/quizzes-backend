package it.sevenbits.web.application.dto.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.web.application.model.Answer;

import java.util.List;

/**
 * GetQuestionResponse class
 */
public class GetQuestionResponse {
    private final String id;
    private final String content;
    private final List<Answer> answers;
    private Answer correctAnswer;

    /**
     *
     * @param id - int
     * @param content - String
     * @param answers - List
     * @param correctAnswer - Answer
     */
    @JsonCreator
    public GetQuestionResponse(@JsonProperty("id") final String id,
                               @JsonProperty("content") final String content,
                               @JsonProperty("answers") final List<Answer> answers,
                               @JsonProperty("correctAnswer") final Answer correctAnswer) {
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

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }
}
