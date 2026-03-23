package dk.project.dao.impl;

import dk.project.entity.Question;
import jakarta.persistence.EntityManager;
import java.util.List;

public class QuestionDAO extends EntityManagerDAO<Question> {

    // Attributes

    // ________________________________________

    public QuestionDAO(EntityManager em){
        super(em, Question.class);
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