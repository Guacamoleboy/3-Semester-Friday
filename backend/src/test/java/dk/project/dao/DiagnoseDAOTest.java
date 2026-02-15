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

}