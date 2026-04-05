package dk.project.service.internal;

import dk.project.dao.impl.QuestionDAO;
import dk.project.entity.Question;
import jakarta.persistence.EntityManager;
import java.util.List;

public class QuestionService extends EntityManagerService<Question> {

    // Attributes
    private final QuestionDAO questionDAO;

    // _________________________________________________________________________________________________________________

    public QuestionService(EntityManager em) {
        super(new QuestionDAO(em), Question.class);
        this.questionDAO = (QuestionDAO) this.entityManagerDAO;
    }

    // _________________________________________________________________________________________________________________

    public List<Question> findByDiagnoseId(int diagnoseId) {
        return questionDAO.findByDiagnoseId(diagnoseId);
    }

    // _________________________________________________________________________________________________________________

    public List<Question> findByMedicationId(int medicationId) {
        return questionDAO.findByMedicationId(medicationId);
    }

    // _________________________________________________________________________________________________________________

    public void updateQuestionValue(Question question, byte value) {
        question.setQuestionValue(value);
        update(question);
    }

}