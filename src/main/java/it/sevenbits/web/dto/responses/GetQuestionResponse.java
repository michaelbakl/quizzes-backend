package it.sevenbits.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.quiz.core.model.Answer;

import java.util.List;

/**
 * GetQuestionResponse class
 */
public class GetQuestionResponse {
    private final String id;
    private final String content;
    private final List<Answer> answers;

    /**
     *
     * @param id - int
     * @param content - String
     * @param answers - List
     */
    @JsonCreator
    public GetQuestionResponse(@JsonProperty("questionId") final String id,
                               @JsonProperty("questionText") final String content,
                               @JsonProperty("answerList") final List<Answer> answers) {
        this.id = id;
        this.content = content;
        this.answers = answers;
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
}
