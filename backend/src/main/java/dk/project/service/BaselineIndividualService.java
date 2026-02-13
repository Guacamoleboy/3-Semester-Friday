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
        baselineIndividualDAO.create(baselineIndividual);
    }

    // ________________________________________

    public void updateBaselineIndividual(BaselineIndividual baselineIndividual){
        validateNotEmpty(baselineIndividual.getBaseline(), "BaselineIndividual.baseline");
        validateNotEmpty(baselineIndividual.getQuestion(), "BaselineIndividual.question");
        baselineIndividualDAO.update(baselineIndividual);
    }

    // ________________________________________

    public void deleteBaselineIndividual(int id){
        validateNotEmpty(id, "BaselineIndividual.id");
        baselineIndividualDAO.deleteById(id);
    }

    // ________________________________________

    public void deleteAllBaselineIndividuals(){
        baselineIndividualDAO.deleteAll();
    }

    // ________________________________________

    public BaselineIndividual getBaselineIndividualById(int id){
        validateNotEmpty(id, "BaselineIndividual.id");
        return baselineIndividualDAO.getById(id);
    }

    // ________________________________________

    public List<BaselineIndividual> getAllBaselineIndividuals(){
        return baselineIndividualDAO.getAll();
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
        return baselineIndividualDAO.findByBaselineId(baselineId);
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