package dk.project.dao;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class BaselineDAOTest extends ADAOTest {

    // Attributes
    private BaselineDAO baselineDAO;

    // ____________________________________________________

    @BeforeEach
    public void setupDAO(){
        this.baselineDAO = new BaselineDAO(em);
    }

}