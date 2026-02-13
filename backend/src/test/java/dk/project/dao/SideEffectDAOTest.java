package dk.project.dao;

import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SideEffectDAOTest extends ADAOTest {

    // Attributes
    private SideEffectDAO sideEffectDAO;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        sideEffectDAO = new SideEffectDAO(em);
        sideEffectDAO.deleteAll();
    }

    // _____________________________________________________
    // FIXME: Add unique methods when they are applied to SideEffectDAO

}