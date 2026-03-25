package dk.project.service;

import dk.project.dao.impl.DiagnoseTypeDAO;
import dk.project.entity.DiagnoseType;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DiagnoseTypeService {

    // Attributes
    private final DiagnoseTypeDAO diagnoseTypeDAO;

    // _________________________________________________________________________________________________________________

    public DiagnoseTypeService(EntityManager em){
        this.diagnoseTypeDAO = new DiagnoseTypeDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public void createDiagnoseType(DiagnoseType diagnoseType){
        validateNotEmpty(diagnoseType.getName(), "DiagnoseType.name");
        diagnoseTypeDAO.create(diagnoseType);
    }

    // _________________________________________________________________________________________________________________

    public void updateDiagnoseType(DiagnoseType diagnoseType){
        validateNotEmpty(diagnoseType.getName(), "DiagnoseType.name");
        diagnoseTypeDAO.update(diagnoseType);
    }

    // _________________________________________________________________________________________________________________

    public void deleteDiagnoseType(int id){
        validateNotEmpty(id, "DiagnoseType.id");
        diagnoseTypeDAO.deleteById(id);
    }

    // _________________________________________________________________________________________________________________

    public void deleteAllDiagnoseTypes(){
        diagnoseTypeDAO.deleteAll();
    }

    // _________________________________________________________________________________________________________________

    public DiagnoseType getDiagnoseTypeById(int id){
        validateNotEmpty(id, "DiagnoseType.id");
        return diagnoseTypeDAO.getById(id);
    }

    // _________________________________________________________________________________________________________________

    public String getNameById(int id) {
        validateNotEmpty(id, "DiagnoseType.id");
        return diagnoseTypeDAO.getColumnById(id, "name");
    }

    // _________________________________________________________________________________________________________________

    public List<DiagnoseType> getAllDiagnoseTypes(){
        List<DiagnoseType> list = diagnoseTypeDAO.getAll();
        return list != null ? list : null;
    }

    // _________________________________________________________________________________________________________________

    public boolean existsByName(String name){
        validateNotEmpty(name, "DiagnoseType.name");
        return diagnoseTypeDAO.existsByName(name);
    }

    // _________________________________________________________________________________________________________________

    public DiagnoseType findByName(String name){
        validateNotEmpty(name, "DiagnoseType.name");
        return diagnoseTypeDAO.findByName(name);
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