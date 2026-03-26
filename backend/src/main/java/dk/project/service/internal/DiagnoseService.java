package dk.project.service.internal;

import dk.project.dao.impl.DiagnoseDAO;
import dk.project.entity.Diagnose;
import jakarta.persistence.EntityManager;

public class DiagnoseService extends EntityManagerService<Diagnose> {

    // Attributes
    private final DiagnoseDAO diagnoseDAO;

    // _________________________________________________________________________________________________________________

    public DiagnoseService(EntityManager em){
        super(new DiagnoseDAO(em), Diagnose.class);
        this.diagnoseDAO = (DiagnoseDAO) this.entityManagerDAO;
    }

    // _________________________________________________________________________________________________________________

    public String getNameById(int id){
        validateNotEmpty(id, "Diagnose.id");
        return getColumnById(id, "name");
    }

    // _________________________________________________________________________________________________________________

    public String getDescriptionById(int id){
        validateNotEmpty(id, "Diagnose.id");
        return getColumnById(id, "description");
    }

    // _________________________________________________________________________________________________________________

    public boolean existsByName(String name) {
        return existByColumn(name, "name");
    }

    // _________________________________________________________________________________________________________________

    public Diagnose findDiagnoseByName(String name){
        return findEntityByColumn(name, "name");
    }

}