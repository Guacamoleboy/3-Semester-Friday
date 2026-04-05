package dk.project.service.internal;

import dk.project.dao.impl.BaselineDAO;
import dk.project.entity.Baseline;
import jakarta.persistence.EntityManager;

public class BaselineService extends EntityManagerService<Baseline> {

    // Attributes
    private final BaselineDAO baselineDAO;

    // _________________________________________________________________________________________________________________

    public BaselineService(EntityManager em) {
        super(new BaselineDAO(em), Baseline.class);
        this.baselineDAO = (BaselineDAO) this.entityManagerDAO;
    }

}