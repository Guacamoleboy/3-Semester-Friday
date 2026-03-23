package dk.project.dao.impl;

import dk.project.entity.Role;
import jakarta.persistence.EntityManager;

public class RoleDAO extends EntityManagerDAO<Role> {

    // Attributes

    // ________________________________________

    public RoleDAO(EntityManager em){
        super(em, Role.class);
    }

}