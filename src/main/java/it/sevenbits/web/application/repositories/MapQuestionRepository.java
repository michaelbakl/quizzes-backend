package it.sevenbits.web.application.repositories;

import it.sevenbits.web.application.model.Answer;
import it.sevenbits.web.application.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


/**
 * question repository implementation
 */
public class MapQuestionRepository implements IQuestionRepository {
    private final Map<String, Question> questionsMap;
    private final Random random;

    /**
     * constructor
     */
    public MapQuestionRepository() {
        questionsMap = new HashMap<>();
        random = new Random();
        fillMapWithQuestionsForTest();
    }

    /**
     * constructor
     * @param questionsMap - Map
     */
    public MapQuestionRepository(final Map<String, Question> questionsMap) {
        this.questionsMap = questionsMap;
        random = new Random();
        fillMapWithQuestionsForTest();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private void fillMapWithQuestionsForTest() {
        int twenty = 20;
        for (int i = 0; i < twenty; i++) {
            List<Answer> list = new ArrayList<>();
            Answer correctAnswer = new Answer(UUID.randomUUID().toString(), "Answer1", 1);
            list.add(correctAnswer);
            list.add(new Answer(UUID.randomUUID().toString(), "Answer2", 0));
            list.add(new Answer(UUID.randomUUID().toString(), "Answer3", 0));
            questionsMap.put(UUID.randomUUID().toString(),
                    new Question(UUID.randomUUID().toString(),
                            "Question " + i,
                            list,
                            correctAnswer));
        }
    }

    @Override
    public Question getQuestion(final String id) {
        return questionsMap.get(id);
    }

    @Override
    public Question getRandomQuestion() {
        return (Question) questionsMap.values().toArray()[random.nextInt(questionsMap.size())];
    }

    @Override
    public List<Question> getListOfRandomQuestion(final int size) {
        List<Question> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(getRandomQuestion());
        }
        return result;
    }

    @Override
    public List<String> getListOfRandomQuestionsIds(final int size) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(getRandomQuestion().getId());
        }
        return result;
    }
}
