package dk.project.service;

import dk.project.dao.RoleDAO;
import jakarta.persistence.EntityManager;

public class RoleService {

    // Attributes

    private final RoleDAO roleDAO;

    // _________________________________________________

    public RoleService(EntityManager em){
        this.roleDAO = new RoleDAO(em);
    }

    // _________________________________________________



}