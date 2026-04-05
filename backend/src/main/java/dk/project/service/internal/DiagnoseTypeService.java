package dk.project.service.internal;

import dk.project.dao.impl.DiagnoseTypeDAO;
import dk.project.entity.DiagnoseType;
import jakarta.persistence.EntityManager;

public class DiagnoseTypeService extends EntityManagerService<DiagnoseType> {

    // Attributes
    private final DiagnoseTypeDAO diagnoseTypeDAO;

    // _________________________________________________________________________________________________________________

    public DiagnoseTypeService(EntityManager em) {
        super(new DiagnoseTypeDAO(em), DiagnoseType.class);
        this.diagnoseTypeDAO = (DiagnoseTypeDAO) this.entityManagerDAO;
    }

    // _________________________________________________________________________________________________________________

    public String getNameById(int id) {
        return diagnoseTypeDAO.getColumnById(id, "name");
    }

    // _________________________________________________________________________________________________________________

    public boolean existsByName(String name) {
        return diagnoseTypeDAO.existsByName(name);
    }

    // _________________________________________________________________________________________________________________

    public DiagnoseType findByName(String name) {
        return diagnoseTypeDAO.findByName(name);
    }

}