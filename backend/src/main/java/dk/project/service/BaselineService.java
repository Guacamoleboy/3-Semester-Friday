package dk.project.service;

import dk.project.dao.BaselineDAO;
import dk.project.entity.Baseline;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BaselineService {

    // Attributes
    private final BaselineDAO baselineDAO;

    // _________________________________________________

    public BaselineService(EntityManager em){
        this.baselineDAO = new BaselineDAO(em);
    }

    // _________________________________________________

    public void createBaseline(Baseline baseline){
        validateNotEmpty(baseline.getClient(), "Baseline.client");
        validateNotEmpty(baseline.getDiagnose(), "Baseline.diagnose");
        validateNotEmpty(baseline.getEndDate(), "Baseline.endDate");
        baselineDAO.create(baseline);
    }

    // _________________________________________________

    public void updateBaseline(Baseline baseline){
        validateNotEmpty(baseline.getClient(), "Baseline.client");
        validateNotEmpty(baseline.getDiagnose(), "Baseline.diagnose");
        validateNotEmpty(baseline.getEndDate(), "Baseline.endDate");
        baselineDAO.update(baseline);
    }

    // _________________________________________________

    public void deleteBaseline(int id){
        validateNotEmpty(id, "Baseline.id");
        baselineDAO.deleteById(id);
    }

    // _________________________________________________

    public void deleteAllBaselines(){
        baselineDAO.deleteAll();
    }

    // _________________________________________________

    public Baseline getBaselineById(int id){
        validateNotEmpty(id, "Baseline.id");
        return baselineDAO.getById(id);
    }

    // _________________________________________________

    public List<Baseline> getAllBaselines(){
        return baselineDAO.getAll();
    }

    // _________________________________________________

    public List<Baseline> findByClientId(String clientId){
        validateNotEmpty(clientId, "Baseline.clientId");
        return baselineDAO.findByClientId(clientId);
    }

    // _________________________________________________

    public List<Baseline> findByDiagnoseId(int diagnoseId){
        validateNotEmpty(diagnoseId, "Baseline.diagnoseId");
        return baselineDAO.findByDiagnoseId(diagnoseId);
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