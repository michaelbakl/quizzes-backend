package it.sevenbits.quiz.core.services.game;

import it.sevenbits.quiz.core.exceptions.QuizErrorCode;
import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.model.Answer;
import it.sevenbits.quiz.core.repositories.game.IGameRepository;
import it.sevenbits.quiz.core.repositories.question.IQuestionRepository;
import it.sevenbits.quiz.core.repositories.room.IRoomRepository;
import it.sevenbits.quiz.core.services.interfaces.IGameService;
import it.sevenbits.quiz.core.model.Game;
import it.sevenbits.quiz.core.model.Question;
import it.sevenbits.quiz.web.dto.responses.question.AnswerQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.GameStatusResponse;
import it.sevenbits.quiz.web.dto.responses.question.GetQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.StartGameDtoResponse;
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
        if (!checkRoomIsInRepo(roomId)) {
            throw new QuizException(QuizErrorCode.ROOM_NOT_FOUND);
        }
        if (checkGameIsRunning(roomId)) {
            throw new QuizException(QuizErrorCode.GAME_ALREADY_EXISTS);
        }
        final int three = 3;
        Game game = new Game(three);
        game.setScore(0);
        game.setQuestionsIds(questionRepository.getListOfRandomQuestionsIds(three));
        game.setQuestionsAmount(three);
        game.setCurrentIdPos(0);
        game.setStatus("Started");
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
        Game currentGame = gameRepository.getGame(roomId);
        currentGame.setStatus("In process");
        if (checkAnswerCorrect(currentQuestion, answerID)) {
            result = currentQuestion.getCorrectAnswer().getPoints();
            currentGame.setCurrentIdPos(currentGame.getCurrentIdPos() + 1);
            currentGame.setScore(currentGame.getScore() + result);
            gameRepository.updateGame(roomId, currentGame);
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

    @Override
    public String getOwnerId(final String roomId) {
        return roomRepository.getRoomById(roomId).getOwnerId();
    }

    private boolean checkQuestionIsInRepo(final String questionId) {
        return questionRepository.checkQuestionExists(questionId);
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

    private boolean checkGameIsRunning(final String roomId) {
        return gameRepository.checkGameIsInProgress(roomId);
    }

    private boolean checkGameFinished(final Game game) {
        return game.getCurrentIdPos() >= game.getQuestionsAmount();
    }
}
