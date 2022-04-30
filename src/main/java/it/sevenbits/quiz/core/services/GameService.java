package it.sevenbits.quiz.core.services;

import it.sevenbits.quiz.core.exceptions.QuizErrorCode;
import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.model.Answer;
import it.sevenbits.quiz.core.repositories.interfaces.IGameRepository;
import it.sevenbits.quiz.core.repositories.interfaces.IQuestionRepository;
import it.sevenbits.quiz.core.repositories.interfaces.IRoomRepository;
import it.sevenbits.quiz.core.services.interfaces.IGameService;
import it.sevenbits.quiz.core.model.Game;
import it.sevenbits.quiz.core.model.Question;
import it.sevenbits.web.dto.responses.AnswerQuestionResponse;
import it.sevenbits.web.dto.responses.GameStatusResponse;
import it.sevenbits.web.dto.responses.GetQuestionResponse;
import it.sevenbits.web.dto.responses.StartGameDtoResponse;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * game service
 */
@Service
public class GameService implements IGameService {
    private final IGameRepository gameRepository;
    private final IQuestionRepository questionRepository;
    private final IRoomRepository roomRepository;

    /**
     * constructor for GameService
     * @param gameRepository - repo for games
     * @param questionRepository - repo for questions
     * @param roomRepository - repo for rooms
     */
    public GameService(final IGameRepository gameRepository,
                       final IQuestionRepository questionRepository,
                       final IRoomRepository roomRepository) {
        this.gameRepository = gameRepository;
        this.questionRepository = questionRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public StartGameDtoResponse startGame(final String roomId) throws QuizException {
        final int ten = 10;
        Game game = new Game(ten);
        game.setScore(0);
        game.setQuestionsIds(questionRepository.getListOfRandomQuestionsIds(ten));
        game.setQuestionsAmount(ten);
        game.setCurrentIdPos(0);
        game.setStatus("Started");
        if (!checkRoomIsInRepo(roomId)) {
            throw new QuizException(QuizErrorCode.ROOM_NOT_FOUND);
        }
        gameRepository.createGame(roomId, game);
        return new StartGameDtoResponse(game.getCurrentQuestionId());
    }

    @Override
    public GetQuestionResponse getQuestion(final String id) throws QuizException {
        if (!checkQuestionIsInRepo(id)) {
            throw new QuizException(QuizErrorCode.QUESTION_NOT_FOUND);
        }
        Question question = questionRepository.getQuestion(id);
        Answer[] answers = question.getAnswers().toArray(new Answer[0]);
        return new GetQuestionResponse(
                question.getId(),
                question.getContent(),
                answers
        );
    }

    @Override
    public AnswerQuestionResponse sendAnswer(final String roomId,
                                             final String playerId,
                                             final String questionId,
                                             final String answerID
    ) throws QuizException {
        if (!checkQuestionIsInRepo(questionId)) {
            throw new QuizException(QuizErrorCode.QUESTION_NOT_FOUND);
        }
        Question currentQuestion = questionRepository.getQuestion(questionId);
        if (!checkRoomIsInRepo(roomId)) {
            throw new QuizException(QuizErrorCode.ROOM_NOT_FOUND);
        }
        if (!checkPlayerIsInRoom(roomId, playerId)) {
            throw new QuizException(QuizErrorCode.PLAYER_IS_NOT_IN_THE_ROOM);
        }
        if (!checkAnswerBelongsToAQuestion(currentQuestion, answerID)) {
            throw new QuizException(QuizErrorCode.ANSWER_DOES_NOT_MATCH_QUESTION);
        }
        int result = 0;
        if (checkPlayerIsInRoom(roomId, playerId) && checkAnswerCorrect(currentQuestion, answerID)) {
            result = currentQuestion.getCorrectAnswer().getPoints();
            gameRepository.updateGameScore(result, roomId);
            roomRepository.updatePlayerScore(roomId, playerId, result);
        }
        return new AnswerQuestionResponse(
                currentQuestion.getCorrectAnswer().getAnswerId(),
                gameRepository.getNextQuestionId(roomId),
                gameRepository.getGameScore(roomId),
                result
        );
    }

    @Override
    public GameStatusResponse getGameStatus(final String roomId) throws QuizException {
        if (checkRoomIsInRepo(roomId)) {
            return new GameStatusResponse(gameRepository.getGame(roomId).getStatus(),
                    gameRepository.getIdOfCurrentQuestion(roomId),
                    gameRepository.getGame(roomId).getCurrentIdPos(),
                    gameRepository.getGame(roomId).getQuestionsAmount());
        } else {
            throw new QuizException(QuizErrorCode.ROOM_NOT_FOUND);
        }
    }

    @Override
    public boolean checkRoomIsInRepo(final String roomId) {
        return roomRepository.checkRoomIsInRepository(roomId);
    }

    private boolean checkQuestionIsInRepo(final String questionId) {
        return questionRepository.getQuestion(questionId) != null;
    }

    private boolean checkAnswerBelongsToAQuestion(final Question question, final String answerId) {
        return question.getAnswersIds().contains(answerId);
    }

    private boolean checkAnswerCorrect(final Question question, final String answerId) {
        return Objects.equals(question.getCorrectAnswer().getAnswerId(), answerId);
    }

    private boolean checkPlayerIsInRoom(final String roomId, final String playerId) {
        return roomRepository.getRoomById(roomId).getPlayerById(playerId) != null;
    }
}
