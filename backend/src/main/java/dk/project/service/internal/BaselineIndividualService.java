package dk.project.service.internal;

import dk.project.dao.impl.BaselineIndividualDAO;
import dk.project.entity.BaselineIndividual;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BaselineIndividualService {

    // Attributes
    private final BaselineIndividualDAO baselineIndividualDAO;

    // _________________________________________________________________________________________________________________

    public BaselineIndividualService(EntityManager em){
        this.baselineIndividualDAO = new BaselineIndividualDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public void createBaselineIndividual(BaselineIndividual baselineIndividual){
        validateNotEmpty(baselineIndividual.getBaseline(), "BaselineIndividual.baseline");
        validateNotEmpty(baselineIndividual.getQuestion(), "BaselineIndividual.question");
        baselineIndividualDAO.create(baselineIndividual);
    }

    // _________________________________________________________________________________________________________________

    public void updateBaselineIndividual(BaselineIndividual baselineIndividual){
        validateNotEmpty(baselineIndividual.getBaseline(), "BaselineIndividual.baseline");
        validateNotEmpty(baselineIndividual.getQuestion(), "BaselineIndividual.question");
        baselineIndividualDAO.update(baselineIndividual);
    }

    // _________________________________________________________________________________________________________________

    public void deleteBaselineIndividual(int id){
        validateNotEmpty(id, "BaselineIndividual.id");
        baselineIndividualDAO.deleteById(id);
    }

    // _________________________________________________________________________________________________________________

    public void deleteAllBaselineIndividuals(){
        baselineIndividualDAO.deleteAll();
    }

    // _________________________________________________________________________________________________________________

    public BaselineIndividual getBaselineIndividualById(int id){
        validateNotEmpty(id, "BaselineIndividual.id");
        return baselineIndividualDAO.getById(id);
    }

    // _________________________________________________________________________________________________________________

    public List<BaselineIndividual> getAllBaselineIndividuals(){
        return baselineIndividualDAO.getAll();
    }

    // _________________________________________________________________________________________________________________

    public void updateValue(BaselineIndividual baselineIndividual, byte value){
        baselineIndividual.setValue(value);
        updateBaselineIndividual(baselineIndividual);
    }

    // _________________________________________________________________________________________________________________

    public void updateNote(BaselineIndividual baselineIndividual, String note){
        baselineIndividual.setNote(note);
        updateBaselineIndividual(baselineIndividual);
    }

    // _________________________________________________________________________________________________________________

    public List<BaselineIndividual> findByBaselineId(int baselineId){
        validateNotEmpty(baselineId, "BaselineIndividual.baselineId");
        return baselineIndividualDAO.findByBaselineId(baselineId);
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