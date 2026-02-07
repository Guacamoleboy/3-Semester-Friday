package dk.project.service;

import dk.project.dao.DiagnoseClientDAO;
import jakarta.persistence.EntityManager;

public class DiagnoseClientService {

    // Attributes

    private final DiagnoseClientDAO diagnoseClientDAO;

    // _________________________________________________

    public DiagnoseClientService(EntityManager em){
        this.diagnoseClientDAO = new DiagnoseClientDAO(em);
    }

    // _________________________________________________



}