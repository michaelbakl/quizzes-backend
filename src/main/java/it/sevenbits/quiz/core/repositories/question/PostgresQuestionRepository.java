package it.sevenbits.quiz.core.repositories.question;

import it.sevenbits.quiz.core.model.Answer;
import it.sevenbits.quiz.core.model.Question;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * implementation of question repository using postgres database
 */
public class PostgresQuestionRepository implements IQuestionRepository {

  private final JdbcOperations jdbcOperations;

  /**
   * constructor
   * @param jdbcOperations - instrument for database
   */
  public PostgresQuestionRepository(final JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  public Question getQuestion(final String id) {
    Question question = jdbcOperations.queryForObject("SELECT * FROM question WHERE questionid = ?",
            (resultSet, i) -> new Question(
                    resultSet.getString("questionid"),
                    resultSet.getString("content"),
                    new ArrayList<>(),
                    jdbcOperations.queryForObject("SELECT * FROM answer WHERE answerid = ?",
                            (resultSet1, j) ->
                                    new Answer(
                                            resultSet1.getString("answerid"),
                                            resultSet1.getString("answertext"),
                                            resultSet1.getInt("points")),
                            resultSet.getString("correctanswerid")
                    )
            ), id);
    assert question != null;
    question.setAnswers(getAnswersToQuestion(question.getId()));
    return question;
  }

  @Override
  public Question getRandomQuestion() {
    Question question = jdbcOperations.queryForObject("SELECT * FROM question ORDER BY random() LIMIT 1",
            (resultSet, i) -> new Question(
                    resultSet.getString("questionid"),
                    resultSet.getString("content"),
                    new ArrayList<>(),
                    jdbcOperations.queryForObject("SELECT * FROM answer WHERE answerid = ?",
                            (resultSet1, j) ->
                                    new Answer(
                                            resultSet1.getString("answerid"),
                                            resultSet1.getString("answertext"),
                                            resultSet1.getInt("points")),
                            resultSet.getString("correctanswerid")
                    )
            ));
    assert question != null;
    question.setAnswers(getAnswersToQuestion(question.getId()));
    return question;
  }

  @Override
  public List<Question> getListOfRandomQuestion(final int size) {
    return jdbcOperations.query("SELECT * FROM question ORDER BY random() LIMIT ?",
            (resultSet, i) -> new Question(
                    resultSet.getString("questionid"),
                    resultSet.getString("content"),
                    getAnswersToQuestion(resultSet.getString("questionid")),
                    jdbcOperations.queryForObject("SELECT * FROM answer WHERE answerid = ?",
                            (resultSet1, j) ->
                                    new Answer(
                                            resultSet1.getString("answerid"),
                                            resultSet1.getString("answertext"),
                                            resultSet1.getInt("points")),
                            resultSet.getString("correctanswerid")
                    )
            ), size);
  }

  @Override
  public List<String> getListOfRandomQuestionsIds(final int size) {
    return jdbcOperations.query("SELECT * FROM question ORDER BY random() LIMIT ?",
            (resultSet, i) -> resultSet.getString("questionid"), size);
  }

  @Override
  public boolean checkQuestionExists(final String questionId) {
    try {
      int checker = jdbcOperations.queryForObject("SELECT COUNT(questionid) AS counter FROM question WHERE questionid =?",
              (resultSet, i) -> resultSet.getInt("counter"), questionId);
      return checker == 1;
    } catch (NullPointerException e) {
      return false;
    }
  }

  private List<Answer> getAnswersToQuestion(final String questionId) {
    return jdbcOperations.query("SELECT answerid FROM answerstoquestions WHERE questionid = ?",
            ((resultSet, i) -> jdbcOperations.queryForObject("SELECT * FROM answer WHERE answerid = ?",
                    (resultSet1, j) ->
                            new Answer(
                                    resultSet1.getString("answerid"),
                                    resultSet1.getString("answertext"),
                                    resultSet1.getInt("points")),
                    resultSet.getString("answerid")
            )), questionId);
  }

}
