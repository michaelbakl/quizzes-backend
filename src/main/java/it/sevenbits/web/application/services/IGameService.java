package it.sevenbits.web.application.services;

import it.sevenbits.web.application.dto.responses.SendAnswerDtoResponse;
import it.sevenbits.web.application.dto.responses.StartGameDtoResponse;
import it.sevenbits.web.application.model.Question;

public interface IGameService {
    StartGameDtoResponse startGame();
    Question getQuestion(String id);
    SendAnswerDtoResponse sendAnswer(String questionId, String answerID);
    void validateAnswer(String id);
}
