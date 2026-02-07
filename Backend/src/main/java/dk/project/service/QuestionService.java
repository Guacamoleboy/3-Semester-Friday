package dk.project.service;

import dk.project.dao.QuestionDAO;
import dk.project.entity.Question;
import jakarta.persistence.EntityManager;
import java.util.List;

public class QuestionService {

    // Attributes
    private final QuestionDAO questionDAO;

    // ________________________________________
    public QuestionService(EntityManager em){
        this.questionDAO = new QuestionDAO(em);
    }

    // ________________________________________

    public void createQuestion(Question question){
        validateNotEmpty(question.getQuestionTitle(), "Question.title");
        validateNotEmpty(question.getQuestionDescription(), "Question.description");
        validateNotEmpty(question.getDiagnosis(), "Question.diagnosis");
        questionDAO.createQuestion(question);
    }

    // ________________________________________

    public void updateQuestion(Question question){
        validateNotEmpty(question.getQuestionTitle(), "Question.title");
        validateNotEmpty(question.getQuestionDescription(), "Question.description");
        validateNotEmpty(question.getDiagnosis(), "Question.diagnosis");
        questionDAO.updateQuestion(question);
    }

    // ________________________________________

    public void deleteQuestion(int id){
        validateNotEmpty(id, "Question.id");
        questionDAO.deleteQuestion(id);
    }

    // ________________________________________

    public int deleteAllQuestions(){
        return questionDAO.deleteAllQuestions();
    }

    // ________________________________________

    public Question getQuestionById(int id){
        validateNotEmpty(id, "Question.id");
        return questionDAO.getQuestionById(id);
    }

    // ________________________________________

    public List<Question> getAllQuestions(){
        List<Question> list = questionDAO.getAllQuestions();
        return list != null ? list : null;
    }

    // ________________________________________

    public List<Question> findByDiagnoseId(int diagnoseId){
        validateNotEmpty(diagnoseId, "Question.diagnoseId");
        List<Question> list = questionDAO.findByDiagnoseId(diagnoseId);
        return list != null ? list : null;
    }

    // ________________________________________

    public List<Question> findByMedicationId(int medicationId){
        validateNotEmpty(medicationId, "Question.medicationId");
        List<Question> list = questionDAO.findByMedicationId(medicationId);
        return list != null ? list : null;
    }

    // ________________________________________

    public void updateQuestionValue(Question question, byte value){
        question.setQuestionValue(value);
        updateQuestion(question);
    }

    // ________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}