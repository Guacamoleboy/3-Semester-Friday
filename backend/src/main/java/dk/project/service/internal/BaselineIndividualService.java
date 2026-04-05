package dk.project.service.internal;

import dk.project.dao.impl.BaselineIndividualDAO;
import dk.project.entity.BaselineIndividual;
import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BaselineIndividualService extends EntityManagerService<BaselineIndividual> {

    // Attributes
    private final BaselineIndividualDAO baselineIndividualDAO;

    // _________________________________________________________________________________________________________________

    public BaselineIndividualService(EntityManager em) {
        super(new BaselineIndividualDAO(em), BaselineIndividual.class);
        this.baselineIndividualDAO = (BaselineIndividualDAO) this.entityManagerDAO;
    }

    // _________________________________________________________________________________________________________________

    public List<SideEffect> getSideEffects(int baselineIndividualId) {
        BaselineIndividual baselineIndividual = getById(baselineIndividualId);
        return baselineIndividual.getSideEffects();
    }

    // _________________________________________________________________________________________________________________

    public void addSideEffect(int baselineIndividualId, SideEffect sideEffect) {
        BaselineIndividual baselineIndividual = getById(baselineIndividualId);
        baselineIndividualDAO.addSideEffect(baselineIndividual, sideEffect);
    }

    // _________________________________________________________________________________________________________________

    public void removeSideEffect(int baselineIndividualId, SideEffect sideEffect) {
        BaselineIndividual baselineIndividual = getById(baselineIndividualId);
        baselineIndividualDAO.removeSideEffect(baselineIndividual, sideEffect);
    }

}