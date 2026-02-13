package dk.project.dao;

import dk.project.entity.Client;
import dk.project.entity.Diagnose;
import dk.project.entity.DiagnoseClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiagnoseClientDAOTest extends ADAOTest {

    // Attributes
    private DiagnoseClientDAO diagnoseClientDAO;
    private EntityManagerDAO<Client> clientDAO;
    private EntityManagerDAO<Diagnose> diagnoseDAO;
    private Client client1;
    private Client client2;
    private Diagnose diagnose1;
    private Diagnose diagnose2;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        diagnoseClientDAO = new DiagnoseClientDAO(em);
        clientDAO = new EntityManagerDAO<>(em, Client.class);
        diagnoseDAO = new EntityManagerDAO<>(em, Diagnose.class);
        diagnoseClientDAO.deleteAll();
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
        diagnose1.setName("Diabetes");
        diagnose1.setDescription("Chronic blood sugar condition");
        diagnoseDAO.create(diagnose1);
        diagnose2 = new Diagnose();
        diagnose2.setName("Hypertension");
        diagnose2.setDescription("High blood pressure condition");
        diagnoseDAO.create(diagnose2);

        // DiagnoseClients
        DiagnoseClient diagnoseClient1 = new DiagnoseClient();
        diagnoseClient1.setClient(client1);
        diagnoseClient1.setDiagnose(diagnose1);
        diagnoseClientDAO.create(diagnoseClient1);
        DiagnoseClient diagnoseClient2 = new DiagnoseClient();
        diagnoseClient2.setClient(client1);
        diagnoseClient2.setDiagnose(diagnose2);
        diagnoseClientDAO.create(diagnoseClient2);
        DiagnoseClient diagnoseClient3 = new DiagnoseClient();
        diagnoseClient3.setClient(client2);
        diagnoseClient3.setDiagnose(diagnose1);
        diagnoseClientDAO.create(diagnoseClient3);
    }

    // _____________________________________________________

    @Test
    public void shouldFindByClientId() {
        List<DiagnoseClient> client1List = diagnoseClientDAO.findByClientId(client1.getId());
        List<DiagnoseClient> client2List = diagnoseClientDAO.findByClientId(client2.getId());

        assertEquals(2, client1List.size());
        assertTrue(client1List.stream().anyMatch(dc -> dc.getDiagnose().getName().equals("Diabetes")));
        assertTrue(client1List.stream().anyMatch(dc -> dc.getDiagnose().getName().equals("Hypertension")));
        assertEquals(1, client2List.size());
        assertEquals("Diabetes", client2List.get(0).getDiagnose().getName());
    }

    // _____________________________________________________

    @Test
    public void shouldFindByDiagnoseId() {
        List<DiagnoseClient> diabetesList = diagnoseClientDAO.findByDiagnoseId(diagnose1.getId());
        List<DiagnoseClient> hypertensionList = diagnoseClientDAO.findByDiagnoseId(diagnose2.getId());

        assertEquals(2, diabetesList.size());
        assertTrue(diabetesList.stream().anyMatch(dc -> dc.getClient().getId().equals("111111-1111")));
        assertTrue(diabetesList.stream().anyMatch(dc -> dc.getClient().getId().equals("222222-2222")));
        assertEquals(1, hypertensionList.size());
        assertEquals("111111-1111", hypertensionList.get(0).getClient().getId());
    }

}