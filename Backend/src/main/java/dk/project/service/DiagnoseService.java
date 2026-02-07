package dk.project.service;

import dk.project.dao.DiagnoseDAO;
import jakarta.persistence.EntityManager;

public class DiagnoseService {

    // Attributes

    private final DiagnoseDAO diagnoseDAO;

    // _________________________________________________

    public DiagnoseService(EntityManager em){
        this.diagnoseDAO = new DiagnoseDAO(em);
    }

    // _________________________________________________



}