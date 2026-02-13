package dk.project.service;

import dk.project.dao.SideEffectDAO;
import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;
import java.util.List;

public class SideEffectService {

    // Attributes
    private final SideEffectDAO sideEffectDAO;

    // _________________________________________________

    public SideEffectService(EntityManager em){
        this.sideEffectDAO = new SideEffectDAO(em);
    }

    // _________________________________________________

    public void createSideEffect(SideEffect sideEffect){
        validateNotEmpty(sideEffect.getNote(), "SideEffect.note");
        sideEffectDAO.create(sideEffect);
    }

    // _________________________________________________

    public void updateSideEffect(SideEffect sideEffect){
        validateNotEmpty(sideEffect.getNote(), "SideEffect.note");
        sideEffectDAO.update(sideEffect);
    }

    // _________________________________________________

    public void deleteSideEffect(int id){
        validateNotEmpty(id, "SideEffect.id");
        sideEffectDAO.deleteById(id);
    }

    // _________________________________________________

    public void deleteAllSideEffects(){
        sideEffectDAO.deleteAll();
    }

    // _________________________________________________

    public SideEffect getSideEffectById(int id){
        validateNotEmpty(id, "SideEffect.id");
        return sideEffectDAO.getById(id);
    }

    // _________________________________________________

    public String getNoteById(int id) {
        validateNotEmpty(id, "SideEffect.id");
        SideEffect sideEffect = sideEffectDAO.getById(id);
        return sideEffect != null ? sideEffect.getNote() : null;
    }

    // _________________________________________________

    public List<SideEffect> getAllSideEffects(){
        List<SideEffect> list = sideEffectDAO.getAll();
        return list != null ? list : null;
    }

    // _________________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}