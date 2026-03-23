package dk.project.dao.impl;

import dk.project.ATest;
import dk.project.entity.Client;
import dk.project.entity.Medication;
import dk.project.entity.MedicationClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MedicationClientDAOTest extends ATest {

    // Attributes
    private MedicationClientDAO medicationClientDAO;
    private EntityManagerDAO<Medication> medicationDAO;
    private EntityManagerDAO<Client> clientDAO;
    private Client client1;
    private Client client2;
    private Medication medication1;
    private Medication medication2;

    // _________________________________________________

    @BeforeEach
    public void setupDAO() {
        medicationClientDAO = new MedicationClientDAO(em);
        medicationDAO = new EntityManagerDAO<>(em, Medication.class);
        clientDAO = new EntityManagerDAO<>(em, Client.class);
        medicationClientDAO.deleteAll();
        medicationDAO.deleteAll();
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

        // Medications
        medication1 = new Medication();
        medication1.setName("Paracetamol");
        medication1.setDescription("Painkiller");
        medicationDAO.create(medication1);
        medication2 = new Medication();
        medication2.setName("Ibuprofen");
        medication2.setDescription("Anti-inflammatory");
        medicationDAO.create(medication2);

        // MedicationClient
        MedicationClient medicationClient1 = new MedicationClient();
        medicationClient1.setClient(client1);
        medicationClient1.setMedication(medication1);
        medicationClient1.setAmount(2);
        medicationClient1.setTimeline("Morning");
        medicationClientDAO.create(medicationClient1);
        MedicationClient medicationClient2 = new MedicationClient();
        medicationClient2.setClient(client1);
        medicationClient2.setMedication(medication2);
        medicationClient2.setAmount(1);
        medicationClient2.setTimeline("Evening");
        medicationClientDAO.create(medicationClient2);
        MedicationClient medicationClient3 = new MedicationClient();
        medicationClient3.setClient(client2);
        medicationClient3.setMedication(medication1);
        medicationClient3.setAmount(1);
        medicationClient3.setTimeline("Morning");
        medicationClientDAO.create(medicationClient3);
    }

    // _________________________________________________

    @Test
    public void shouldFindByClientId() {
        List<MedicationClient> client1Meds = medicationClientDAO.findByClientId(client1.getId());
        List<MedicationClient> client2Meds = medicationClientDAO.findByClientId(client2.getId());

        assertEquals(2, client1Meds.size());
        assertTrue(client1Meds.stream().anyMatch(mc -> mc.getMedication().getName().equals("Paracetamol")));
        assertTrue(client1Meds.stream().anyMatch(mc -> mc.getMedication().getName().equals("Ibuprofen")));

        assertEquals(1, client2Meds.size());
        assertEquals("Paracetamol", client2Meds.get(0).getMedication().getName());
    }

    // _________________________________________________

    @Test
    public void shouldFindByMedicationId() {
        List<MedicationClient> med1Clients = medicationClientDAO.findByMedicationId(medication1.getId());
        List<MedicationClient> med2Clients = medicationClientDAO.findByMedicationId(medication2.getId());

        assertEquals(2, med1Clients.size());
        assertTrue(med1Clients.stream().anyMatch(mc -> mc.getClient().getId().equals("123456-7890")));
        assertTrue(med1Clients.stream().anyMatch(mc -> mc.getClient().getId().equals("987654-3210")));

        assertEquals(1, med2Clients.size());
        assertEquals("123456-7890", med2Clients.get(0).getClient().getId());
    }

}