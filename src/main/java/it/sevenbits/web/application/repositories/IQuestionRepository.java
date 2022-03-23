package it.sevenbits.web.application.repositories;

import it.sevenbits.web.application.model.Question;

import java.util.List;

public interface IQuestionRepository {
    Question getQuestion(String id);
    Question getRandomQuestion();
    List<Question> getListOfRandomQuestion(int size);
    List<String> getListOfRandomQuestionsIds(int size);

}
