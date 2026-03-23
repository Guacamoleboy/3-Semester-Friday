package dk.project.dao;

import dk.project.ATest;
import dk.project.entity.Diagnose;
import dk.project.entity.Medication;
import dk.project.entity.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionDAOTest extends ATest {

    // Attributes
    private QuestionDAO questionDAO;
    private EntityManagerDAO<Diagnose> diagnoseDAO;
    private EntityManagerDAO<Medication> medicationDAO;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        questionDAO = new QuestionDAO(em);
        diagnoseDAO = new EntityManagerDAO<>(em, Diagnose.class);
        medicationDAO = new EntityManagerDAO<>(em, Medication.class);
        questionDAO.deleteAll();
        diagnoseDAO.deleteAll();
        medicationDAO.deleteAll();
    }

    // _____________________________________________________

    @Test
    public void shouldFindQuestionsByDiagnoseId() {
        // Arrange
        Diagnose diagnose1 = new Diagnose();
        diagnose1.setName("Diagnose A");
        diagnoseDAO.create(diagnose1);

        Diagnose diagnose2 = new Diagnose();
        diagnose2.setName("Diagnose B");
        diagnoseDAO.create(diagnose2);

        Question question1 = new Question();
        question1.setQuestionTitle("Q1");
        question1.setQuestionDescription("Desc1");
        question1.setDiagnosis(diagnose1);
        questionDAO.create(question1);

        Question question2 = new Question();
        question2.setQuestionTitle("Q2");
        question2.setQuestionDescription("Desc2");
        question2.setDiagnosis(diagnose1);
        questionDAO.create(question2);

        Question question3 = new Question();
        question3.setQuestionTitle("Q3");
        question3.setQuestionDescription("Desc3");
        question3.setDiagnosis(diagnose2);
        questionDAO.create(question3);

        em.clear();

        // Act
        List<Question> result = questionDAO.findByDiagnoseId(diagnose1.getId());

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(q -> q.getQuestionTitle().equals("Q1")));
        assertTrue(result.stream().anyMatch(q -> q.getQuestionTitle().equals("Q2")));
    }

    // _____________________________________________________

    @Test
    public void shouldFindQuestionsByMedicationId() {
        // Arrange
        Medication medication1 = new Medication();
        medication1.setName("Med A");
        medicationDAO.create(medication1);

        Medication medication2 = new Medication();
        medication2.setName("Med B");
        medicationDAO.create(medication2);

        Question question1 = new Question();
        question1.setQuestionTitle("Q1");
        question1.setQuestionDescription("Desc1");
        question1.setMedication(medication1);
        questionDAO.create(question1);

        Question question2 = new Question();
        question2.setQuestionTitle("Q2");
        question2.setQuestionDescription("Desc2");
        question2.setMedication(medication1);
        questionDAO.create(question2);

        Question question3 = new Question();
        question3.setQuestionTitle("Q3");
        question3.setQuestionDescription("Desc3");
        question3.setMedication(medication2);
        questionDAO.create(question3);

        em.clear();

        // Act
        List<Question> result = questionDAO.findByMedicationId(medication1.getId());

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(q -> q.getQuestionTitle().equals("Q1")));
        assertTrue(result.stream().anyMatch(q -> q.getQuestionTitle().equals("Q2")));
    }

}