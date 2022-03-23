package it.sevenbits.web.application.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int score;
    private int questionsAmount;
    private List<String> questionsIds;
    private int currentIdPos;

    public Game(int score, int questionsAmount, List<String> questionsIds) {
        this.score = score;
        this.questionsAmount = questionsAmount;
        this.questionsIds = questionsIds;
        currentIdPos = 0;
    }

    public Game(int score, int questionsAmount) {
        this.score = score;
        this.questionsAmount = questionsAmount;
        questionsIds = new ArrayList<>();
        currentIdPos = 0;
    }

    public Game(int questionsAmount) {
        score = 0;
        this.questionsAmount = questionsAmount;
        questionsIds = new ArrayList<>();
        currentIdPos = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestionsAmount() {
        return questionsAmount;
    }

    public void setQuestionsAmount(int questionsAmount) {
        this.questionsAmount = questionsAmount;
    }

    public List<String> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(List<String> questionsIds) {
        this.questionsIds = questionsIds;
    }

    public String getCurrentQuestionId() {
        return questionsIds.get(currentIdPos);
    }

    public void setCurrentId(int idPos) {
        currentIdPos = idPos;
    }

    public String getNextId(){
        currentIdPos++;
        if (currentIdPos < questionsAmount) {
            return questionsIds.get(currentIdPos);
        }
        else return "";
    }
}
