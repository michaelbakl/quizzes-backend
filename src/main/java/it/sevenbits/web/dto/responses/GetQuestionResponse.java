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
    private final Answer[] answersList;

    /**
     *
     * @param id - int
     * @param content - String
     * @param answersList - List
     */
    @JsonCreator
    public GetQuestionResponse(@JsonProperty("questionId") final String id,
                               @JsonProperty("questionText") final String content,
                               @JsonProperty("answersList") final Answer[] answersList) {
        this.id = id;
        this.content = content;
        this.answersList = answersList;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Answer[] getAnswers() {
        return answersList;
    }
}
