package it.sevenbits.web.application.services;

import it.sevenbits.web.application.dto.responses.GetQuestionResponse;
import it.sevenbits.web.application.dto.responses.SendAnswerDtoResponse;
import it.sevenbits.web.application.dto.responses.StartGameDtoResponse;
import it.sevenbits.web.application.model.Game;
import it.sevenbits.web.application.model.Question;
import it.sevenbits.web.application.repositories.GameRepository;
import it.sevenbits.web.application.repositories.IGameRepository;
import it.sevenbits.web.application.repositories.IQuestionRepository;
import it.sevenbits.web.application.repositories.MapQuestionRepository;

import java.util.Objects;

/**
 * game service
 */
public class GameService implements IGameService {
    private final IGameRepository gameRepository;
    private final IQuestionRepository questionRepository;

    /**
     * constructor
     */
    public GameService() {
        this.gameRepository = GameRepository.getGameRepository();
        this.questionRepository = MapQuestionRepository.getQuestionRepository();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
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
    public GetQuestionResponse getQuestion(final String id) {
        Question question = questionRepository.getQuestion(id);
        return new GetQuestionResponse(
                question.getId(),
                question.getContent(),
                question.getAnswers(),
                question.getCorrectAnswer()
        );
    }

    @Override
    public SendAnswerDtoResponse sendAnswer(final String questionId, final String answerID) {
        Question currentQuestion = questionRepository.getQuestion(questionId);
        int result = 0;
        if (checkAnswerCorrect(currentQuestion, answerID)) {
            result = currentQuestion.getCorrectAnswer().getPoints();
            gameRepository.updateGameScore(result);
        }
        return new SendAnswerDtoResponse(
                currentQuestion.getCorrectAnswer().getId(),
                gameRepository.getNextQuestionId(),
                gameRepository.getGameScore(),
                result
        );
    }

    @Override
    public void validateAnswer(final String id) {

    }

    private boolean checkAnswerCorrect(final Question question, final String answerId) {
        return Objects.equals(question.getCorrectAnswer().getId(), answerId);
    }
}
