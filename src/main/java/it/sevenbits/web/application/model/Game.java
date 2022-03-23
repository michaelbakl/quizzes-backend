package it.sevenbits.web.application.model;

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

    /**
     * constructor
     *
     * @param score - int
     * @param questionsAmount - int
     * @param questionsIds - List
     */
    public Game(final int score, final int questionsAmount, final List<String> questionsIds) {
        this.score = score;
        this.questionsAmount = questionsAmount;
        this.questionsIds = questionsIds;
        currentIdPos = 0;
    }

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

    public void setCurrentId(final int idPos) {
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
}
