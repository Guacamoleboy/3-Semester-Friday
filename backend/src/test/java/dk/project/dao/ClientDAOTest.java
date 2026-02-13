package dk.project.dao;

import dk.project.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientDAOTest extends ADAOTest {

    // Attributes
    private ClientDAO clientDAO;
    private Client client1;
    private Client client2;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        clientDAO = new ClientDAO(em);
        clientDAO.deleteAll();

        // Clients
        client1 = new Client();
        client1.setId("123456-7890");
        client1.setIdEnding(7890);
        clientDAO.create(client1);
        client2 = new Client();
        client2.setId("987654-3210");
        client2.setIdEnding(3210);
        clientDAO.create(client2);
    }

    // _____________________________________________________

    @Test
    public void shouldReturnCorrectIdEndingById() {
        Integer idEnding1 = clientDAO.getIdEndingById(client1.getId());
        Integer idEnding2 = clientDAO.getIdEndingById(client2.getId());

        assertEquals(7890, idEnding1);
        assertEquals(3210, idEnding2);
    }

    // _____________________________________________________

    @Test
    public void shouldCheckIfClientExistsById() {
        assertTrue(clientDAO.existsById(client1.getId()));
        assertTrue(clientDAO.existsById(client2.getId()));
        assertFalse(clientDAO.existsById("000000-0000"));
    }

}