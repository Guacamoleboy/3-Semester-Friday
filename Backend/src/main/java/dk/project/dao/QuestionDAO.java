package dk.project.dao;

import dk.project.entity.Question;
import jakarta.persistence.EntityManager;
import java.util.List;

public class QuestionDAO extends EntityManagerDAO {

    // Attributes

    // ________________________________________

    public QuestionDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createQuestion(Question question){
        executeQuery(() -> em.persist(question));
    }

    // ________________________________________

    public void updateQuestion(Question question){
        executeQuery(() -> em.merge(question));
    }

    // ________________________________________

    public void deleteQuestion(int id){
        executeQuery(() -> {
            Question q = em.find(Question.class, id);
            if (q != null) em.remove(q);
        });
    }

    // ________________________________________

    public int deleteAllQuestions(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM Question x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public Question getQuestionById(int id){
        return executeQuery(() -> em.find(Question.class, id));
    }

    // ________________________________________

    public List<Question> getAllQuestions(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Question x";
            return em.createQuery(JPQL, Question.class)
            .getResultList();
        });
    }

    // ________________________________________

    public List<Question> findByDiagnoseId(int diagnoseId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Question x WHERE x.diagnosis.id = :diagnoseId";
            return em.createQuery(JPQL, Question.class)
            .setParameter("diagnoseId", diagnoseId)
            .getResultList();
        });
    }

    // ________________________________________

    public List<Question> findByMedicationId(int medicationId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Question x WHERE x.medication.id = :medicationId";
            return em.createQuery(JPQL, Question.class)
            .setParameter("medicationId", medicationId)
            .getResultList();
        });
    }

}