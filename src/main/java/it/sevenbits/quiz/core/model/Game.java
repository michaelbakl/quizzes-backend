package it.sevenbits.quiz.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * model game class
 */
public class Game {
    private int score;
    private int questionsAmount;
    private List<String> questionsIds;
    private int currentIdPos;

    private String status;

    /**
     * constructor
     *
     * @param score - int
     * @param questionsAmount - int
     */
    public Game(final int score, final int questionsAmount) {
        this.score = score;
        this.questionsAmount = questionsAmount;
        questionsIds = new ArrayList<>();
        currentIdPos = 0;
        status = "new";
    }

    /**
     * constructor
     *
     * @param questionsAmount - int
     */
    public Game(final int questionsAmount) {
        score = 0;
        this.questionsAmount = questionsAmount;
        questionsIds = new ArrayList<>();
        currentIdPos = 0;
        status = "new";
    }

    /**
     * constructor with all params
     * @param score - game score
     * @param questionsAmount - amount of questions
     * @param questionsIds - list of questions ids in game
     * @param currentIdPos - position of current question in list
     * @param status - game status
     */
    public Game(final int score,
                final int questionsAmount,
                final List<String> questionsIds,
                final int currentIdPos,
                final String status) {
        this.score = score;
        this.questionsAmount = questionsAmount;
        this.questionsIds = questionsIds;
        this.currentIdPos = currentIdPos;
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    public int getQuestionsAmount() {
        return questionsAmount;
    }

    public void setQuestionsAmount(final int questionsAmount) {
        this.questionsAmount = questionsAmount;
    }

    public List<String> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(final List<String> questionsIds) {
        this.questionsIds = questionsIds;
    }

    public String getCurrentQuestionId() {
        return questionsIds.get(currentIdPos);
    }

    public int getCurrentIdPos() {
        return currentIdPos;
    }

    public void setCurrentIdPos(final int idPos) {
        currentIdPos = idPos;
    }

    /**
     * getNextId method
     * @return String - id of the next question
     */
    public String getNextId() {
        currentIdPos++;
        if (currentIdPos < questionsAmount) {
            return questionsIds.get(currentIdPos);
        } else {
            return "";
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
