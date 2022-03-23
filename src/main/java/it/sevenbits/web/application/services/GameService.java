package it.sevenbits.web.application.services;

import it.sevenbits.web.application.dto.responses.SendAnswerDtoResponse;
import it.sevenbits.web.application.dto.responses.StartGameDtoResponse;
import it.sevenbits.web.application.model.Game;
import it.sevenbits.web.application.model.Question;
import it.sevenbits.web.application.repositories.IGameRepository;
import it.sevenbits.web.application.repositories.IQuestionRepository;

public class GameService implements IGameService {
    private IGameRepository gameRepository;
    private IQuestionRepository questionRepository;

    public GameService(IGameRepository gameRepository, IQuestionRepository questionRepository) {
        this.gameRepository = gameRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public StartGameDtoResponse startGame() {
        Game game = gameRepository.getGame();
        game.setScore(0);
        game.setQuestionsIds(questionRepository.getListOfRandomQuestionsIds(10));
        game.setQuestionsAmount(10);
        game.setCurrentId(0);
        return new StartGameDtoResponse(game.getCurrentQuestionId());
    }

    @Override
    public Question getQuestion(String id) {
        return questionRepository.getQuestion(id);
    }

    @Override
    public SendAnswerDtoResponse sendAnswer(String questionId, String answerID) {
        int result = 0;
        if (checkAnswerCorrect(questionId, answerID)) {
            result = 1;
        }
        gameRepository.updateGameScore(result);
        SendAnswerDtoResponse response = new SendAnswerDtoResponse(
                result,
                gameRepository.getGameScore(),
                gameRepository.getNextQuestionId()
        );
        return response;
    }

    @Override
    public void validateAnswer(String id) {

    }

    private boolean checkAnswerCorrect (String questionId, String answerId) {
        return questionRepository.getQuestion(questionId).getCorrectAnswer().getId().equals(answerId);
    }
}
