package dk.project.dao.impl;

import dk.project.ATest;
import dk.project.entity.Medication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MedicationDAOTest extends ATest {

    // Attributes
    private MedicationDAO medicationDAO;

    // _________________________________________________

    @BeforeEach
    public void setupDAO() {
        medicationDAO = new MedicationDAO(em);
        medicationDAO.deleteAll();
    }

    // _________________________________________________

    @Test
    public void shouldFindByName() {
        // Arrange
        Medication medication = new Medication();
        medication.setName("Ibuprofen");
        medication.setDescription("Anti-inflammatory");
        medicationDAO.create(medication);

        // Act
        Medication foundMedication = medicationDAO.findByName("Ibuprofen");

        // Assert
        assertNotNull(foundMedication);
        assertEquals("Ibuprofen", foundMedication.getName());
        assertEquals("Anti-inflammatory", foundMedication.getDescription());
    }

    // _________________________________________________

    @Test
    public void shouldReturnTrueIfExistsByName() {
        // Arrange
        Medication medication = new Medication();
        medication.setName("Aspirin");
        medication.setDescription("Blood thinner");
        medicationDAO.create(medication);

        // Act
        boolean exists = medicationDAO.existsByName("Aspirin");

        // Assert
        assertTrue(exists);
    }

    // _________________________________________________

    @Test
    public void shouldReturnFalseIfNotExistsByName() {
        // Act
        boolean exists = medicationDAO.existsByName("NonExistentMed");

        // Assert
        assertFalse(exists);
    }

}