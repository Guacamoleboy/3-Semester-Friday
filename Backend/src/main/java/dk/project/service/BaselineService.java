package dk.project.service;

import dk.project.dao.BaselineDAO;
import jakarta.persistence.EntityManager;

public class BaselineService {

    // Attributes

    private final BaselineDAO baselineDAO;

    // _________________________________________________

    public BaselineService(EntityManager em){
        this.baselineDAO = new BaselineDAO(em);
    }

    // _________________________________________________



}