package dk.project.service;

import dk.project.dao.DiagnoseDAO;
import dk.project.entity.Diagnose;
import jakarta.persistence.EntityManager;

public class DiagnoseService extends EntityManagerService<Diagnose> {

    // Attributes
    private final DiagnoseDAO diagnoseDAO;

    // _________________________________________________

    public DiagnoseService(EntityManager em){
        super(new DiagnoseDAO(em), Diagnose.class);
        this.diagnoseDAO = (DiagnoseDAO) this.entityManagerDAO;
    }

    // _________________________________________________

    public String getNameById(int id){
        validateNotEmpty(id, "Diagnose.id");
        return getColumnById(id, "name");
    }

    // _________________________________________________

    public String getDescriptionById(int id){
        validateNotEmpty(id, "Diagnose.id");
        return getColumnById(id, "description");
    }

    // _________________________________________________

    public boolean existsByName(String name) {
        return existByColumn(name, "name");
    }

    // _________________________________________________

    public Diagnose findDiagnoseByName(String name){
        return findEntityByColumn(name, "name");
    }

}