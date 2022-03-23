package it.sevenbits.web.application.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int score;
    private int questionsAmount;
    private List<Integer> questionsIds;
    private int currentId;

    public Game(int score, int questionsAmount, List<Integer> questionsIds) {
        this.score = score;
        this.questionsAmount = questionsAmount;
        this.questionsIds = questionsIds;
        currentId = questionsIds.get(0);
    }

    public Game(int score, int questionsAmount) {
        this.score = score;
        this.questionsAmount = questionsAmount;
        questionsIds = new ArrayList<>();
        currentId = 0;
    }

    public Game(int questionsAmount) {
        score = 0;
        this.questionsAmount = questionsAmount;
        questionsIds = new ArrayList<>();
        currentId = 0;
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

    public List<Integer> getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(List<Integer> questionsIds) {
        this.questionsIds = questionsIds;
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public int getNextId(){
        currentId++;
        if (currentId < questionsAmount) {
            return questionsIds.get(currentId);
        }
        else return -1;
    }
}
