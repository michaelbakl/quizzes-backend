package it.sevenbits.quiz.web.dto.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.sevenbits.quiz.core.model.Answer;

/**
 * GetQuestionResponse class
 */
public class GetQuestionResponse {
    @JsonProperty
    private final String questionId;
    @JsonProperty
    private final String questionText;
    @JsonProperty
    private final Answer[] answersList;

    /**
     *
     * @param id - int
     * @param content - String
     * @param answersList - array of Answer
     */
    @JsonCreator
    public GetQuestionResponse(@JsonProperty("questionId") final String id,
                               @JsonProperty("questionText") final String content,
                               @JsonProperty("answersList") final Answer[] answersList) {
        this.questionId = id;
        this.questionText = content;
        this.answersList = answersList;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Answer[] getAnswersList() {
        return answersList;
    }
}
