package dk.project.dao;

import dk.project.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleDAOTest extends ADAOTest {

    // Attributes
    private RoleDAO roleDAO;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        roleDAO = new RoleDAO(em);
        roleDAO.deleteAll();
    }

}