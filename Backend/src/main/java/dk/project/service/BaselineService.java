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
        baselineDAO.createBaseline(baseline);
    }

    // _________________________________________________

    public void updateBaseline(Baseline baseline){
        validateNotEmpty(baseline.getClient(), "Baseline.client");
        validateNotEmpty(baseline.getDiagnose(), "Baseline.diagnose");
        validateNotEmpty(baseline.getEndDate(), "Baseline.endDate");
        baselineDAO.updateBaseline(baseline);
    }

    // _________________________________________________

    public void deleteBaseline(int id){
        validateNotEmpty(id, "Baseline.id");
        baselineDAO.deleteBaseline(id);
    }

    // _________________________________________________

    public int deleteAllBaselines(){
        return baselineDAO.deleteAllBaselines();
    }

    // _________________________________________________

    public Baseline getBaselineById(int id){
        validateNotEmpty(id, "Baseline.id");
        return baselineDAO.getBaselineById(id);
    }

    // _________________________________________________

    public List<Baseline> getAllBaselines(){
        List<Baseline> list = baselineDAO.getAllBaselines();
        return list != null ? list : null;
    }

    // _________________________________________________

    public List<Baseline> findByClientId(String clientId){
        validateNotEmpty(clientId, "Baseline.clientId");
        List<Baseline> list = baselineDAO.findByClientId(clientId);
        return list != null ? list : null;
    }

    // _________________________________________________

    public List<Baseline> findByDiagnoseId(int diagnoseId){
        validateNotEmpty(diagnoseId, "Baseline.diagnoseId");
        List<Baseline> list = baselineDAO.findByDiagnoseId(diagnoseId);
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