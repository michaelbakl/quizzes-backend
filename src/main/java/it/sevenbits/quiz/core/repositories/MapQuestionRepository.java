package it.sevenbits.quiz.core.repositories;

import it.sevenbits.quiz.core.model.Answer;
import it.sevenbits.quiz.core.model.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


/**
 * question repository implementation
 */
@Repository
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
        int twenty = 2 * 2 * 2 * 2 + 2 + 2;
        for (int i = 0; i < twenty; i++) {
            List<Answer> list = new ArrayList<>();
            Answer correctAnswer = new Answer(String.valueOf(UUID.randomUUID()), "Answer1", 1);
            list.add(correctAnswer);
            list.add(new Answer(String.valueOf(UUID.randomUUID()), "Answer2", 0));
            list.add(new Answer(String.valueOf(UUID.randomUUID()), "Answer3", 0));
            UUID uuid = UUID.randomUUID();
            questionsMap.put(String.valueOf(uuid),
                    new Question(String.valueOf(uuid),
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
        List<Question> questions = new ArrayList<>(this.questionsMap.values());
        return questions.get(random.nextInt(questionsMap.size()));
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
