package dk.project.service;

import dk.project.dao.BaselineIndividualDAO;
import dk.project.entity.BaselineIndividual;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BaselineIndividualService {

    // Attributes
    private final BaselineIndividualDAO baselineIndividualDAO;

    // ________________________________________

    public BaselineIndividualService(EntityManager em){
        this.baselineIndividualDAO = new BaselineIndividualDAO(em);
    }

    // ________________________________________

    public void createBaselineIndividual(BaselineIndividual baselineIndividual){
        validateNotEmpty(baselineIndividual.getBaseline(), "BaselineIndividual.baseline");
        validateNotEmpty(baselineIndividual.getQuestion(), "BaselineIndividual.question");
        baselineIndividualDAO.createBaselineIndividual(baselineIndividual);
    }

    // ________________________________________

    public void updateBaselineIndividual(BaselineIndividual baselineIndividual){
        validateNotEmpty(baselineIndividual.getBaseline(), "BaselineIndividual.baseline");
        validateNotEmpty(baselineIndividual.getQuestion(), "BaselineIndividual.question");
        baselineIndividualDAO.updateBaselineIndividual(baselineIndividual);
    }

    // ________________________________________

    public void deleteBaselineIndividual(int id){
        validateNotEmpty(id, "BaselineIndividual.id");
        baselineIndividualDAO.deleteBaselineIndividual(id);
    }

    // ________________________________________

    public int deleteAllBaselineIndividuals(){
        return baselineIndividualDAO.deleteAllBaselineIndividuals();
    }

    // ________________________________________

    public BaselineIndividual getBaselineIndividualById(int id){
        validateNotEmpty(id, "BaselineIndividual.id");
        return baselineIndividualDAO.getBaselineIndividualById(id);
    }

    // ________________________________________

    public List<BaselineIndividual> getAllBaselineIndividuals(){
        List<BaselineIndividual> list = baselineIndividualDAO.getAllBaselineIndividuals();
        return list != null ? list : null;
    }

    // ________________________________________

    public void updateValue(BaselineIndividual baselineIndividual, byte value){
        baselineIndividual.setValue(value);
        updateBaselineIndividual(baselineIndividual);
    }

    // ________________________________________

    public void updateNote(BaselineIndividual baselineIndividual, String note){
        baselineIndividual.setNote(note);
        updateBaselineIndividual(baselineIndividual);
    }

    // ________________________________________

    public List<BaselineIndividual> findByBaselineId(int baselineId){
        validateNotEmpty(baselineId, "BaselineIndividual.baselineId");
        List<BaselineIndividual> list = baselineIndividualDAO.findByBaselineId(baselineId);
        return list != null ? list : null;
    }

    // ________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}