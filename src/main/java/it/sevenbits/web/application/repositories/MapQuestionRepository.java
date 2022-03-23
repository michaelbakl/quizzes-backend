package it.sevenbits.web.application.repositories;

import it.sevenbits.web.application.model.Answer;
import it.sevenbits.web.application.model.Question;

import java.util.*;

public class MapQuestionRepository implements IQuestionRepository {
    private static final MapQuestionRepository INSTANCE = new MapQuestionRepository();
    private final Map<Integer, Question> questionsMap;
    private final Random random;

    private MapQuestionRepository() {
        questionsMap = new HashMap<>();
        random = new Random();
    }

    private MapQuestionRepository(Map<Integer, Question> questionsMap) {
        this.questionsMap = questionsMap;
        random = new Random();
    }

    public static MapQuestionRepository getRepository() {
        return INSTANCE;
    }

    private void fillMapWithQuestionsForTest () {
        for (int i = 0; i < 20; i++) {
            List<Answer> list = new ArrayList<>();
            Answer correctAnswer = new Answer(100-i, "Answer1", 1);
            list.add(correctAnswer);
            list.add(new Answer(200-i, "Answer2", 0));
            list.add(new Answer(300-i, "Answer3", 0));
            questionsMap.put(i, new Question(i, "Question " + String.valueOf(i), list, correctAnswer));
        }
    }

    @Override
    public Question getQuestion(int id) {
        return questionsMap.get(id);
    }

    @Override
    public Question getRandomQuestion() {
        return questionsMap.get(random.nextInt(questionsMap.size()));
    }

    @Override
    public List<Question> getListOfRandomQuestion(int size) {
        List<Question> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(questionsMap.get(random.nextInt(questionsMap.size())));
        }
        return result;
    }

    @Override
    public List<Integer> getListOfRandomQuestionsIds(int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(questionsMap.get(random.nextInt(questionsMap.size())).getId());
        }
        return result;
    }
}
