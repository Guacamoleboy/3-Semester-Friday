package dk.project.service;

import dk.project.dao.impl.SideEffectDAO;
import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;
import java.util.List;

public class SideEffectService {

    // Attributes
    private final SideEffectDAO sideEffectDAO;

    // _________________________________________________________________________________________________________________

    public SideEffectService(EntityManager em){
        this.sideEffectDAO = new SideEffectDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public void createSideEffect(SideEffect sideEffect){
        validateNotEmpty(sideEffect.getNote(), "SideEffect.note");
        sideEffectDAO.create(sideEffect);
    }

    // _________________________________________________________________________________________________________________

    public void updateSideEffect(SideEffect sideEffect){
        validateNotEmpty(sideEffect.getNote(), "SideEffect.note");
        sideEffectDAO.update(sideEffect);
    }

    // _________________________________________________________________________________________________________________

    public void deleteSideEffect(int id){
        validateNotEmpty(id, "SideEffect.id");
        sideEffectDAO.deleteById(id);
    }

    // _________________________________________________________________________________________________________________

    public void deleteAllSideEffects(){
        sideEffectDAO.deleteAll();
    }

    // _________________________________________________________________________________________________________________

    public SideEffect getSideEffectById(int id){
        validateNotEmpty(id, "SideEffect.id");
        return sideEffectDAO.getById(id);
    }

    // _________________________________________________________________________________________________________________

    public String getNoteById(int id) {
        validateNotEmpty(id, "SideEffect.id");
        SideEffect sideEffect = sideEffectDAO.getById(id);
        return sideEffect != null ? sideEffect.getNote() : null;
    }

    // _________________________________________________________________________________________________________________

    public List<SideEffect> getAllSideEffects(){
        List<SideEffect> list = sideEffectDAO.getAll();
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}