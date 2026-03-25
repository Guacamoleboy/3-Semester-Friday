package dk.project.service;

import dk.project.dao.impl.QuestionDAO;
import dk.project.entity.Question;
import jakarta.persistence.EntityManager;
import java.util.List;

public class QuestionService {

    // Attributes
    private final QuestionDAO questionDAO;

    // _________________________________________________________________________________________________________________

    public QuestionService(EntityManager em){
        this.questionDAO = new QuestionDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public void createQuestion(Question question){
        validateNotEmpty(question.getQuestionTitle(), "Question.title");
        validateNotEmpty(question.getQuestionDescription(), "Question.description");
        validateNotEmpty(question.getDiagnosis(), "Question.diagnosis");
        questionDAO.create(question);
    }

    // _________________________________________________________________________________________________________________

    public void updateQuestion(Question question){
        validateNotEmpty(question.getQuestionTitle(), "Question.title");
        validateNotEmpty(question.getQuestionDescription(), "Question.description");
        validateNotEmpty(question.getDiagnosis(), "Question.diagnosis");
        questionDAO.update(question);
    }

    // _________________________________________________________________________________________________________________

    public void deleteQuestion(int id){
        validateNotEmpty(id, "Question.id");
        questionDAO.deleteById(id);
    }

    // _________________________________________________________________________________________________________________

    public void deleteAllQuestions(){
        questionDAO.deleteAll();
    }

    // _________________________________________________________________________________________________________________

    public Question getQuestionById(int id){
        validateNotEmpty(id, "Question.id");
        return questionDAO.getById(id);
    }

    // _________________________________________________________________________________________________________________

    public List<Question> getAllQuestions(){
        List<Question> list = questionDAO.getAll();
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    public List<Question> findByDiagnoseId(int diagnoseId){
        validateNotEmpty(diagnoseId, "Question.diagnoseId");
        List<Question> list = questionDAO.findByDiagnoseId(diagnoseId);
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    public List<Question> findByMedicationId(int medicationId){
        validateNotEmpty(medicationId, "Question.medicationId");
        List<Question> list = questionDAO.findByMedicationId(medicationId);
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    public void updateQuestionValue(Question question, byte value){
        question.setQuestionValue(value);
        updateQuestion(question);
    }

    // _________________________________________________________________________________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}