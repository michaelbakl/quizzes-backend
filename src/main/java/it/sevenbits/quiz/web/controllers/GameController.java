package it.sevenbits.quiz.web.controllers;

import it.sevenbits.quiz.core.exceptions.QuizErrorCode;
import it.sevenbits.quiz.core.exceptions.QuizException;
import it.sevenbits.quiz.core.services.interfaces.IGameService;
import it.sevenbits.quiz.web.dto.requests.question.AnswerQuestionRequest;
import it.sevenbits.quiz.web.dto.responses.game.StartGameDtoResponse;
import it.sevenbits.quiz.web.dto.responses.question.AnswerQuestionResponse;
import it.sevenbits.quiz.web.dto.responses.game.GameStatusResponse;
import it.sevenbits.quiz.web.dto.responses.question.GetQuestionResponse;
import it.sevenbits.quiz.web.security.AuthRoleRequired;
import it.sevenbits.quiz.web.security.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Game controller
 */
@Controller
@RequestMapping("/rooms/{roomId}/game")
public class GameController {
    private final Logger logger = LoggerFactory.getLogger(GameController.class);

    private final IGameService gameService;

    /**
     * constructor
     *
     * @param gameService - GameService
     */
    public GameController(final IGameService gameService) {
        this.gameService = gameService;
    }

    /**
     * starts game
     * @param roomId - String
     * @param userCredentials - user info
     * @return StartGameDtoResponse
     */
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @AuthRoleRequired("USER")
    ResponseEntity<StartGameDtoResponse> startGame(@PathVariable("roomId") final String roomId,
                                                   final UserCredentials userCredentials) {
        try {
            if (!isUUID(roomId)) {
                throw new QuizException(QuizErrorCode.WRONG_INPUTS);
            }
            if (!userCredentials.getUserId().equals(gameService.getOwnerId(roomId))) {
                throw new QuizException(QuizErrorCode.NOT_AN_OWNER);
            }
            StartGameDtoResponse response = gameService.startGame(roomId);
            logger.info("Game started");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (QuizException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * get question method: gets question from repository and returns it
     * @param roomId - String
     * @param questionId - String
     * @return GetQuestionResponse
     */
    @RequestMapping(value = "/question/{questionId}", method = RequestMethod.GET)
    @ResponseBody
    @AuthRoleRequired("USER")
    public ResponseEntity<GetQuestionResponse> getQuestion(
            @PathVariable("roomId") final String roomId,
            @PathVariable("questionId") final String questionId
    ) {
        try {
            if (!isUUID(roomId) || !isUUID(questionId)) {
                throw new QuizException(QuizErrorCode.WRONG_INPUTS);
            }
            GetQuestionResponse questionResponse = gameService.getQuestion(questionId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(questionResponse);
        } catch (QuizException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * sends answer to a question
     * @param questionId - String
     * @param roomId - String
     * @param answerQuestionRequest - AnswerQuestionRequest
     * @param userCredentials - user info
     * @return ResponseEntity
     */
    @RequestMapping(value = "/question/{questionId}/answer", method = RequestMethod.POST)
    @ResponseBody
    @AuthRoleRequired("USER")
    public ResponseEntity<AnswerQuestionResponse> sendAnswer(
            @RequestBody final AnswerQuestionRequest answerQuestionRequest,
            @PathVariable("roomId") final String roomId,
            @PathVariable("questionId") final String questionId,
            final UserCredentials userCredentials
    ) {
        try {
            if (!isUUID(roomId)
                    || !isUUID(questionId)
                    || !isUUID(answerQuestionRequest.getAnswerId())
            ) {
                throw new QuizException(QuizErrorCode.WRONG_INPUTS);
            }
            AnswerQuestionResponse response =
                    gameService.sendAnswer(roomId,
                            userCredentials.getUserId(),
                            questionId,
                            answerQuestionRequest.getAnswerId());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (QuizException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * gets game status
     * @param roomId - String
     * @return GameStatusResponse
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @AuthRoleRequired("USER")
    public ResponseEntity<GameStatusResponse> getGameStatus(
            @PathVariable("roomId") final String roomId
    ) {
        try {
            if (roomId == null || "".equals(roomId) || !isUUID(roomId)) {
                throw new QuizException(QuizErrorCode.WRONG_INPUTS);
            }
            GameStatusResponse gameStatusResponse = gameService.getGameStatus(roomId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(gameStatusResponse);
        } catch (QuizException e) {
            GameStatusResponse gameStatusResponse =
                    new GameStatusResponse("INVALID", "INVALID", -1, -1);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(gameStatusResponse);
        }
    }

    private boolean isUUID(final String testUUID) {
        if (testUUID == null) {
            return false;
        }
        return java.util.regex.Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}")
                .matcher(testUUID).find();
    }

}
