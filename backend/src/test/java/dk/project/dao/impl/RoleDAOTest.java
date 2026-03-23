package dk.project.dao.impl;

import dk.project.ATest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleDAOTest extends ATest {

    // Attributes
    private RoleDAO roleDAO;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        roleDAO = new RoleDAO(em);
        roleDAO.deleteAll();
    }

}