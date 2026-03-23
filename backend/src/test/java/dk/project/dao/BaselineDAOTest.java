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
public class BaselineDAOTest extends ATest {

    // Attributes
    private BaselineDAO baselineDAO;
    private EntityManagerDAO<Client> clientDAO;
    private EntityManagerDAO<Diagnose> diagnoseDAO;
    private Client client1;
    private Client client2;
    private Diagnose diagnose1;
    private Diagnose diagnose2;
    private Baseline baseline1;
    private Baseline baseline2;
    private Baseline baseline3;

    // _________________________________________________________

    @BeforeEach
    public void setupDAO() {
        baselineDAO = new BaselineDAO(em);
        clientDAO = new EntityManagerDAO<>(em, Client.class);
        diagnoseDAO = new EntityManagerDAO<>(em, Diagnose.class);
        baselineDAO.deleteAll();
        clientDAO.deleteAll();
        diagnoseDAO.deleteAll();

        // Clients
        client1 = new Client();
        client1.setId("111111-1111");
        client1.setIdEnding(1111);
        clientDAO.create(client1);
        client2 = new Client();
        client2.setId("222222-2222");
        client2.setIdEnding(2222);
        clientDAO.create(client2);

        // Diagnoses
        diagnose1 = new Diagnose();
        diagnose1.setName("Diagnosis A");
        diagnoseDAO.create(diagnose1);
        diagnose2 = new Diagnose();
        diagnose2.setName("Diagnosis B");
        diagnoseDAO.create(diagnose2);

        // Baselines
        baseline1 = new Baseline();
        baseline1.setClient(client1);
        baseline1.setDiagnose(diagnose1);
        baseline1.setEndDate(new Timestamp(System.currentTimeMillis()));
        baselineDAO.create(baseline1);
        baseline2 = new Baseline();
        baseline2.setClient(client1);
        baseline2.setDiagnose(diagnose2);
        baseline2.setEndDate(new Timestamp(System.currentTimeMillis()));
        baselineDAO.create(baseline2);
        baseline3 = new Baseline();
        baseline3.setClient(client2);
        baseline3.setDiagnose(diagnose1);
        baseline3.setEndDate(new Timestamp(System.currentTimeMillis()));
        baselineDAO.create(baseline3);
    }

    // _________________________________________________

    @Test
    public void shouldFindBaselinesByClientId() {
        // Act
        List<Baseline> client1Baselines = baselineDAO.findByClientId(client1.getId());
        List<Baseline> client2Baselines = baselineDAO.findByClientId(client2.getId());

        // Assert
        assertEquals(2, client1Baselines.size());
        assertTrue(client1Baselines.stream().anyMatch(b -> b.getId() == baseline1.getId()));
        assertTrue(client1Baselines.stream().anyMatch(b -> b.getId() == baseline2.getId()));
        assertEquals(1, client2Baselines.size());
        assertEquals(baseline3.getId(), client2Baselines.get(0).getId());
    }

    // _________________________________________________

    @Test
    public void shouldFindBaselinesByDiagnoseId() {
        // Act
        List<Baseline> diagnose1Baselines = baselineDAO.findByDiagnoseId(diagnose1.getId());
        List<Baseline> diagnose2Baselines = baselineDAO.findByDiagnoseId(diagnose2.getId());

        // Assert
        assertEquals(2, diagnose1Baselines.size());
        assertTrue(diagnose1Baselines.stream().anyMatch(b -> b.getId() == baseline1.getId()));
        assertTrue(diagnose1Baselines.stream().anyMatch(b -> b.getId() == baseline3.getId()));
        assertEquals(1, diagnose2Baselines.size());
        assertEquals(baseline2.getId(), diagnose2Baselines.get(0).getId());
    }

}