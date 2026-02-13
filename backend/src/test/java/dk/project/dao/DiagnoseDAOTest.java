package dk.project.dao;

import dk.project.entity.Diagnose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiagnoseDAOTest extends ADAOTest {

    // Attributes
    private DiagnoseDAO diagnoseDAO;
    private Diagnose sampleDiagnose;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        diagnoseDAO = new DiagnoseDAO(em);
        diagnoseDAO.deleteAll();

        sampleDiagnose = new Diagnose();
        sampleDiagnose.setName("Diabetes");
        sampleDiagnose.setDescription("Chronic condition related to blood sugar");
        diagnoseDAO.create(sampleDiagnose);
    }

    // _____________________________________________________

    @Test
    public void shouldExistByName() {
        assertTrue(diagnoseDAO.existsByName("Diabetes"));
        assertFalse(diagnoseDAO.existsByName("Hypertension"));
    }

    // _____________________________________________________

    @Test
    public void shouldFindByName() {
        Diagnose foundDiagnose = diagnoseDAO.findByName("Diabetes");
        assertNotNull(foundDiagnose);
        assertEquals(sampleDiagnose.getId(), foundDiagnose.getId());
        assertEquals("Diabetes", foundDiagnose.getName());
        assertEquals("Chronic condition related to blood sugar", foundDiagnose.getDescription());
    }

}