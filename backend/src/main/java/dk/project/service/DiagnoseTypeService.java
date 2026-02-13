package dk.project.service;

import dk.project.dao.DiagnoseTypeDAO;
import dk.project.entity.DiagnoseType;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DiagnoseTypeService {

    // Attributes
    private final DiagnoseTypeDAO diagnoseTypeDAO;

    // _________________________________________________

    public DiagnoseTypeService(EntityManager em){
        this.diagnoseTypeDAO = new DiagnoseTypeDAO(em);
    }

    // _________________________________________________

    public void createDiagnoseType(DiagnoseType diagnoseType){
        validateNotEmpty(diagnoseType.getName(), "DiagnoseType.name");
        diagnoseTypeDAO.create(diagnoseType);
    }

    // _________________________________________________

    public void updateDiagnoseType(DiagnoseType diagnoseType){
        validateNotEmpty(diagnoseType.getName(), "DiagnoseType.name");
        diagnoseTypeDAO.update(diagnoseType);
    }

    // _________________________________________________

    public void deleteDiagnoseType(int id){
        validateNotEmpty(id, "DiagnoseType.id");
        diagnoseTypeDAO.deleteById(id);
    }

    // _________________________________________________

    public void deleteAllDiagnoseTypes(){
        diagnoseTypeDAO.deleteAll();
    }

    // _________________________________________________

    public DiagnoseType getDiagnoseTypeById(int id){
        validateNotEmpty(id, "DiagnoseType.id");
        return diagnoseTypeDAO.getById(id);
    }

    // _________________________________________________

    public String getNameById(int id) {
        validateNotEmpty(id, "DiagnoseType.id");
        return diagnoseTypeDAO.getColumnById(id, "name");
    }

    // _________________________________________________

    public List<DiagnoseType> getAllDiagnoseTypes(){
        List<DiagnoseType> list = diagnoseTypeDAO.getAll();
        return list != null ? list : null;
    }

    // _________________________________________________

    public boolean existsByName(String name){
        validateNotEmpty(name, "DiagnoseType.name");
        return diagnoseTypeDAO.existsByName(name);
    }

    // _________________________________________________

    public DiagnoseType findByName(String name){
        validateNotEmpty(name, "DiagnoseType.name");
        return diagnoseTypeDAO.findByName(name);
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