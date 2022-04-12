package it.sevenbits.quiz.core.services;

import it.sevenbits.quiz.core.model.Answer;
import it.sevenbits.quiz.core.repositories.IGameRepository;
import it.sevenbits.quiz.core.repositories.IQuestionRepository;
import it.sevenbits.web.dto.responses.GetQuestionResponse;
import it.sevenbits.web.dto.responses.AnswerQuestionResponse;
import it.sevenbits.web.dto.responses.StartGameDtoResponse;
import it.sevenbits.quiz.core.model.Game;
import it.sevenbits.quiz.core.model.Question;
import it.sevenbits.quiz.core.repositories.GameRepository;
import it.sevenbits.quiz.core.repositories.MapQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * game service
 */
@Service
public class GameService {
    private final IGameRepository gameRepository;
    private final IQuestionRepository questionRepository;

    /**
     * constructor
     */
    public GameService() {
        this.gameRepository = GameRepository.getGameRepository();
        this.questionRepository = MapQuestionRepository.getQuestionRepository();
    }

    /**
     * start game method
     * @return start game dto response
     */
    public StartGameDtoResponse startGame() {
        int ten = 2 + 2 + 2 + 2 + 2;
        Game game = gameRepository.getGame();
        game.setScore(0);
        game.setQuestionsIds(questionRepository.getListOfRandomQuestionsIds(ten));
        game.setQuestionsAmount(ten);
        game.setCurrentIdPos(0);
        return new StartGameDtoResponse(game.getCurrentQuestionId());
    }

    /**
     * get question method
     * @param id - String
     * @return AnswerQuestionResponse
     */
    public GetQuestionResponse getQuestion(final String id) {
        Question question = questionRepository.getQuestion(id);
        Answer[] answers = question.getAnswers().toArray(new Answer[0]);
        return new GetQuestionResponse(
                question.getId(),
                question.getContent(),
                answers
        );
    }

    /**
     * send answer method
     * @param questionId - String
     * @param answerID - String
     * @return AnswerQuestionResponse
     */
    public AnswerQuestionResponse sendAnswer(final String questionId, final String answerID) {
        Question currentQuestion = questionRepository.getQuestion(questionId);
        int result = 0;
        if (checkAnswerCorrect(currentQuestion, answerID)) {
            result = currentQuestion.getCorrectAnswer().getPoints();
            gameRepository.updateGameScore(result);
        }
        return new AnswerQuestionResponse(
                currentQuestion.getCorrectAnswer().getAnswerId(),
                gameRepository.getNextQuestionId(),
                gameRepository.getGameScore(),
                result
        );
    }

    private boolean checkAnswerCorrect(final Question question, final String answerId) {
        return Objects.equals(question.getCorrectAnswer().getAnswerId(), answerId);
    }
}
