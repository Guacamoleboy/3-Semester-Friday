package dk.project.dao.impl;

import dk.project.ATest;
import dk.project.entity.DiagnoseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiagnoseTypeDAOTest extends ATest {

    // Attributes
    private DiagnoseTypeDAO diagnoseTypeDAO;
    private DiagnoseType type1;
    private DiagnoseType type2;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        diagnoseTypeDAO = new DiagnoseTypeDAO(em);
        diagnoseTypeDAO.deleteAll();

        type1 = new DiagnoseType();
        type1.setName("TypeA");
        diagnoseTypeDAO.create(type1);
        type2 = new DiagnoseType();
        type2.setName("TypeB");
        diagnoseTypeDAO.create(type2);
    }

    // _____________________________________________________

    @Test
    public void shouldExistByName() {
        assertTrue(diagnoseTypeDAO.existsByName("TypeA"));
        assertTrue(diagnoseTypeDAO.existsByName("TypeB"));
        assertFalse(diagnoseTypeDAO.existsByName("NonExistentType"));
    }

    // _____________________________________________________

    @Test
    public void shouldFindByName() {
        DiagnoseType found1 = diagnoseTypeDAO.findByName("TypeA");
        DiagnoseType found2 = diagnoseTypeDAO.findByName("TypeB");

        assertNotNull(found1);
        assertEquals(type1.getId(), found1.getId());
        assertEquals("TypeA", found1.getName());
        assertNotNull(found2);
        assertEquals(type2.getId(), found2.getId());
        assertEquals("TypeB", found2.getName());
    }

}