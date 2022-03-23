package it.sevenbits.web.application.services;

import it.sevenbits.web.application.dto.responses.SendAnswerDtoResponse;
import it.sevenbits.web.application.dto.responses.StartGameDtoResponse;
import it.sevenbits.web.application.model.Question;

public interface IGameService {
    StartGameDtoResponse startGame();
    Question getQuestion(int id);
    SendAnswerDtoResponse sendAnswer(int questionId, int answerID);
    void validateAnswer(int id);
}
