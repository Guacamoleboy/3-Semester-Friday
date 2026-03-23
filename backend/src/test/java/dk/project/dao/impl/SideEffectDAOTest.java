package dk.project.dao.impl;

import dk.project.ATest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SideEffectDAOTest extends ATest {

    // Attributes
    private SideEffectDAO sideEffectDAO;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        sideEffectDAO = new SideEffectDAO(em);
        sideEffectDAO.deleteAll();
    }

    // _____________________________________________________

}