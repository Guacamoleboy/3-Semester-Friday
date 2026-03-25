package dk.project.service;

import dk.project.dao.impl.BaselineDAO;
import dk.project.entity.Baseline;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BaselineService {

    // Attributes
    private final BaselineDAO baselineDAO;

    // _________________________________________________________________________________________________________________

    public BaselineService(EntityManager em){
        this.baselineDAO = new BaselineDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public void createBaseline(Baseline baseline){
        validateNotEmpty(baseline.getClient(), "Baseline.client");
        validateNotEmpty(baseline.getDiagnose(), "Baseline.diagnose");
        validateNotEmpty(baseline.getEndDate(), "Baseline.endDate");
        baselineDAO.create(baseline);
    }

    // _________________________________________________________________________________________________________________

    public void updateBaseline(Baseline baseline){
        validateNotEmpty(baseline.getClient(), "Baseline.client");
        validateNotEmpty(baseline.getDiagnose(), "Baseline.diagnose");
        validateNotEmpty(baseline.getEndDate(), "Baseline.endDate");
        baselineDAO.update(baseline);
    }

    // _________________________________________________________________________________________________________________

    public void deleteBaseline(int id){
        validateNotEmpty(id, "Baseline.id");
        baselineDAO.deleteById(id);
    }

    // _________________________________________________________________________________________________________________

    public void deleteAllBaselines(){
        baselineDAO.deleteAll();
    }

    // _________________________________________________________________________________________________________________

    public Baseline getBaselineById(int id){
        validateNotEmpty(id, "Baseline.id");
        return baselineDAO.getById(id);
    }

    // _________________________________________________________________________________________________________________

    public List<Baseline> getAllBaselines(){
        return baselineDAO.getAll();
    }

    // _________________________________________________________________________________________________________________

    public List<Baseline> findByClientId(String clientId){
        validateNotEmpty(clientId, "Baseline.clientId");
        return baselineDAO.findByClientId(clientId);
    }

    // _________________________________________________________________________________________________________________

    public List<Baseline> findByDiagnoseId(int diagnoseId){
        validateNotEmpty(diagnoseId, "Baseline.diagnoseId");
        return baselineDAO.findByDiagnoseId(diagnoseId);
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