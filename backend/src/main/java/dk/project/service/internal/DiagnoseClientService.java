package dk.project.service.internal;

import dk.project.dao.impl.DiagnoseClientDAO;
import dk.project.entity.DiagnoseClient;
import jakarta.persistence.EntityManager;

public class DiagnoseClientService extends EntityManagerService<DiagnoseClient> {

    // Attributes
    private final DiagnoseClientDAO diagnoseClientDAO;

    // _________________________________________________________________________________________________________________

    public DiagnoseClientService(EntityManager em) {
        super(new DiagnoseClientDAO(em), DiagnoseClient.class);
        this.diagnoseClientDAO = (DiagnoseClientDAO) this.entityManagerDAO;
    }

}