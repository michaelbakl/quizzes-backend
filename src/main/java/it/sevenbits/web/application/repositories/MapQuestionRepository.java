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
public final class MapQuestionRepository implements IQuestionRepository {
    private static MapQuestionRepository mapQuestionRepository;
    private final Map<String, Question> questionsMap;
    private final Random random;

    /**
     * constructor
     */
    private MapQuestionRepository() {
        questionsMap = new HashMap<>();
        random = new Random();
        fillMapWithQuestionsForTest();
    }

    /**
     * singlton method
     * @return MapQuestionRepository
     */
    public static MapQuestionRepository getQuestionRepository() {
        if (mapQuestionRepository == null) {
            mapQuestionRepository = new MapQuestionRepository();
        }
        return mapQuestionRepository;
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
            UUID uuid = UUID.randomUUID();
            questionsMap.put(uuid.toString(),
                    new Question(uuid.toString(),
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
