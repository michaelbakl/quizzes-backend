package it.sevenbits.web.application.services;

import it.sevenbits.web.application.dto.responses.SendAnswerDtoResponse;
import it.sevenbits.web.application.dto.responses.StartGameDtoResponse;
import it.sevenbits.web.application.model.Game;
import it.sevenbits.web.application.model.Question;
import it.sevenbits.web.application.repositories.IGameRepository;
import it.sevenbits.web.application.repositories.IQuestionRepository;

/**
 * game service
 */
public class GameService implements IGameService {
    private final IGameRepository gameRepository;
    private final IQuestionRepository questionRepository;

    /**
     * constructor
     *
     * @param gameRepository     - IGameRepository
     * @param questionRepository - IQuestionRepository
     */
    public GameService(final IGameRepository gameRepository, final IQuestionRepository questionRepository) {
        this.gameRepository = gameRepository;
        this.questionRepository = questionRepository;
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
    public Question getQuestion(final String id) {
        return questionRepository.getQuestion(id);
    }

    @Override
    public SendAnswerDtoResponse sendAnswer(final String questionId, final String answerID) {
        int result = 0;
        if (checkAnswerCorrect(questionId, answerID)) {
            result = 1;
        }
        gameRepository.updateGameScore(result);
        return new SendAnswerDtoResponse(
                result,
                gameRepository.getGameScore(),
                gameRepository.getNextQuestionId()
        );
    }

    @Override
    public void validateAnswer(final String id) {

    }

    private boolean checkAnswerCorrect(final String questionId, final String answerId) {
        return questionRepository.getQuestion(questionId).getCorrectAnswer().getId().equals(answerId);
    }
}
