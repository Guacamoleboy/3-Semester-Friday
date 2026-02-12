package dk.project.service;

import dk.project.dao.DiagnoseDAO;
import dk.project.entity.Diagnose;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DiagnoseService {

    // Attributes
    private final DiagnoseDAO diagnoseDAO;

    // _________________________________________________

    public DiagnoseService(EntityManager em){
        this.diagnoseDAO = new DiagnoseDAO(em);
    }

    // _________________________________________________

    public void createDiagnose(Diagnose diagnose){
        validateNotEmpty(diagnose.getName(), "Diagnose.name");
        diagnoseDAO.create(diagnose);
    }

    // _________________________________________________

    public void updateDiagnose(Diagnose diagnose){
        validateNotEmpty(diagnose.getName(), "Diagnose.name");
        diagnoseDAO.update(diagnose);
    }

    // _________________________________________________

    public void deleteDiagnose(Object id){
        validateNotEmpty(id, "Diagnose.id");
        diagnoseDAO.deleteById(id);
    }

    // _________________________________________________

    public void deleteAllDiagnoses(){
        diagnoseDAO.deleteAll();
    }

    // _________________________________________________

    public Diagnose getDiagnoseById(Object id){
        validateNotEmpty(id, "Diagnose.id");
        return diagnoseDAO.getById(id);
    }

    // _________________________________________________

    public String getNameById(int id){
        validateNotEmpty(id, "Diagnose.id");
        return diagnoseDAO.getNameById(id);
    }

    // _________________________________________________

    public String getDescriptionById(int id){
        validateNotEmpty(id, "Diagnose.id");
        return diagnoseDAO.getDescriptionById(id);
    }

    // _________________________________________________

    public List<Diagnose> getAllDiagnoses(){
        List<Diagnose> diagnoses = diagnoseDAO.getAll();
        return diagnoses != null ? diagnoses : null;
    }

    // _________________________________________________

    public boolean existsByName(String name){
        validateNotEmpty(name, "Diagnose.name");
        return diagnoseDAO.existsByName(name);
    }

    // _________________________________________________

    public Diagnose findByName(String name){
        validateNotEmpty(name, "Diagnose.name");
        return diagnoseDAO.findByName(name);
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