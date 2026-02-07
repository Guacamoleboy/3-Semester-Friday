package dk.project.service;

import dk.project.dao.BaselineIndividualDAO;
import jakarta.persistence.EntityManager;

public class BaselineIndividualService {

    // Attributes

    private final BaselineIndividualDAO baselineIndividualDAO;

    // _________________________________________________

    public BaselineIndividualService(EntityManager em){
        this.baselineIndividualDAO = new BaselineIndividualDAO(em);
    }

    // _________________________________________________



}