package dk.project.service;

import dk.project.dao.DiagnoseTypeDAO;
import jakarta.persistence.EntityManager;

public class DiagnoseTypeService {

    // Attributes

    private final DiagnoseTypeDAO diagnoseTypeDAO;

    // _________________________________________________

    public DiagnoseTypeService(EntityManager em){
        this.diagnoseTypeDAO = new DiagnoseTypeDAO(em);
    }

    // _________________________________________________



}