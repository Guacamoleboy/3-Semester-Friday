package dk.project.dao;

import dk.project.ATest;
import dk.project.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.sql.Timestamp;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaselineIndividualDAOTest extends ATest {

    // Attributes
    private BaselineIndividualDAO baselineIndividualDAO;
    private EntityManagerDAO<Baseline> baselineDAO;
    private EntityManagerDAO<Client> clientDAO;
    private EntityManagerDAO<Diagnose> diagnoseDAO;
    private EntityManagerDAO<SideEffect> sideEffectDAO;
    private EntityManagerDAO<Question> questionDAO;
    private BaselineIndividual baselineIndividual1;
    private BaselineIndividual baselineIndividual2;
    private Baseline baseline1;
    private Client client1;
    private Diagnose diagnose1;
    private Question question1;
    private Question question2;
    private SideEffect sideEffect1;
    private SideEffect sideEffect2;

    // _________________________________________________________

    @BeforeEach
    public void setupDAO() {
        baselineIndividualDAO = new BaselineIndividualDAO(em);
        baselineDAO = new EntityManagerDAO<>(em, Baseline.class);
        clientDAO = new EntityManagerDAO<>(em, Client.class);
        questionDAO = new EntityManagerDAO<>(em, Question.class);
        diagnoseDAO = new EntityManagerDAO<>(em, Diagnose.class);
        sideEffectDAO = new EntityManagerDAO<>(em, SideEffect.class);

        baselineIndividualDAO.deleteAll();
        baselineDAO.deleteAll();
        clientDAO.deleteAll();
        questionDAO.deleteAll();
        diagnoseDAO.deleteAll();
        sideEffectDAO.deleteAll();

        // Client
        client1 = new Client();
        client1.setId("123456-7890");
        client1.setIdEnding(7890);
        clientDAO.create(client1);

        // Diagnose
        diagnose1 = new Diagnose();
        diagnose1.setName("Diagnosis 1");
        diagnoseDAO.create(diagnose1);

        // Baseline
        baseline1 = new Baseline();
        baseline1.setClient(client1);
        baseline1.setDiagnose(diagnose1);
        baseline1.setEndDate(new Timestamp(System.currentTimeMillis()));
        baselineDAO.create(baseline1);

        // Question
        question1 = new Question();
        question1.setQuestionTitle("Q1");
        question1.setQuestionDescription("Question");
        question1.setDiagnosis(diagnose1);
        questionDAO.create(question1);
        question2 = new Question();
        question2.setQuestionTitle("Q2");
        question2.setQuestionDescription("Second Question");
        question2.setDiagnosis(diagnose1);
        questionDAO.create(question2);

        // BaselineIndividual
        baselineIndividual1 = new BaselineIndividual();
        baselineIndividual1.setBaseline(baseline1);
        baselineIndividual1.setQuestion(question1);
        baselineIndividualDAO.create(baselineIndividual1);
        baselineIndividual2 = new BaselineIndividual();
        baselineIndividual2.setBaseline(baseline1);
        baselineIndividual2.setQuestion(question2);
        baselineIndividualDAO.create(baselineIndividual2);

        // SideEffects
        sideEffect1 = new SideEffect();
        sideEffect1.setNote("Headache");
        sideEffectDAO.create(sideEffect1);
        sideEffect2 = new SideEffect();
        sideEffect2.setNote("Nausea");
        sideEffectDAO.create(sideEffect2);
    }

    // _________________________________________________

    @Test
    public void shouldAddSideEffectToBaselineIndividual() {
        // Act
        baselineIndividualDAO.addSideEffect(baselineIndividual1, sideEffect1);

        // Assert
        BaselineIndividual fetched = baselineIndividualDAO.getById(baselineIndividual1.getId());
        assertTrue(fetched.getSideEffects().contains(sideEffect1));
        assertEquals(1, fetched.getSideEffects().size());
    }

    // _________________________________________________

    @Test
    public void shouldRemoveSideEffectFromBaselineIndividual() {
        // Arrange
        baselineIndividualDAO.addSideEffect(baselineIndividual1, sideEffect1);
        baselineIndividualDAO.addSideEffect(baselineIndividual1, sideEffect2);

        // Act
        baselineIndividualDAO.removeSideEffect(baselineIndividual1, sideEffect1);

        // Assert
        BaselineIndividual fetchedBaselineIndividual = baselineIndividualDAO.getById(baselineIndividual1.getId());
        assertFalse(fetchedBaselineIndividual.getSideEffects().contains(sideEffect1));
        assertTrue(fetchedBaselineIndividual.getSideEffects().contains(sideEffect2));
        assertEquals(1, fetchedBaselineIndividual.getSideEffects().size());
    }

    // _________________________________________________

    @Test
    public void shouldFindIndividualsByBaselineId() {

        // Act
        List<BaselineIndividual> baselineIndividuals = baselineIndividualDAO.findByBaselineId(baseline1.getId());

        // Assert
        assertEquals(2, baselineIndividuals.size());
        assertTrue(baselineIndividuals.stream().anyMatch(i -> i.getId() == baselineIndividual1.getId()));
        assertTrue(baselineIndividuals.stream().anyMatch(i -> i.getId() == baselineIndividual2.getId()));
    }

}