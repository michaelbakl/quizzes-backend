package it.sevenbits.quiz.core.repositories.question;

import it.sevenbits.quiz.core.model.Question;

import java.util.List;

/**
 * interface for question repository
 */
public interface IQuestionRepository {
    /**
     * getter for question
     * @param id - String
     * @return Question
     */
    Question getQuestion(String id);

    /**
     * getter for random question
     * @return Question
     */
    Question getRandomQuestion();

    /**
     * getter for list of random questions
     * @param size - int
     * @return List
     */
    List<Question> getListOfRandomQuestion(int size);

    /**
     * getter for list of random questions` ids
     * @param size - int
     * @return List
     */
    List<String> getListOfRandomQuestionsIds(int size);

    /**
     * check if question exists in repository
     * @param questionId - id of question
     * @return true if question is in repository, false otherwise
     */
    boolean checkQuestionExists(String questionId);
}
