package dk.project.service;

import dk.project.dao.SideEffectDAO;
import jakarta.persistence.EntityManager;

public class SideEffectService {

    // Attributes

    private final SideEffectDAO sideEffectDAO;

    // _________________________________________________

    public SideEffectService(EntityManager em){
        this.sideEffectDAO = new SideEffectDAO(em);
    }

    // _________________________________________________



}