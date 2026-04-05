package dk.project.service.internal;

import dk.project.dao.impl.SideEffectDAO;
import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;

public class SideEffectService extends EntityManagerService<SideEffect> {

    // Attributes
    private final SideEffectDAO sideEffectDAO;

    // _________________________________________________________________________________________________________________

    public SideEffectService(EntityManager em) {
        super(new SideEffectDAO(em), SideEffect.class);
        this.sideEffectDAO = (SideEffectDAO) this.entityManagerDAO;
    }

    // _________________________________________________________________________________________________________________

    public String getNoteById(int id) {
        SideEffect sideEffect = getById(id);
        return sideEffect.getNote();
    }

}