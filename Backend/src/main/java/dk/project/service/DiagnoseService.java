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
        diagnoseDAO.createDiagnose(diagnose);
    }

    // _________________________________________________

    public void updateDiagnose(Diagnose diagnose){
        validateNotEmpty(diagnose.getName(), "Diagnose.name");
        diagnoseDAO.updateDiagnose(diagnose);
    }

    // _________________________________________________

    public void deleteDiagnose(int id){
        validateNotEmpty(id, "Diagnose.id");
        diagnoseDAO.deleteDiagnose(id);
    }

    // _________________________________________________

    public int deleteAllDiagnoses(){
        return diagnoseDAO.deleteAllDiagnoses();
    }

    // _________________________________________________

    public Diagnose getDiagnoseById(int id){
        validateNotEmpty(id, "Diagnose.id");
        return diagnoseDAO.getDiagnoseById(id);
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
        List<Diagnose> diagnoses = diagnoseDAO.getAllDiagnoses();
        if(diagnoses != null){
            return diagnoses;
        }
        return null;
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