package dk.project.route.impl;

import dk.project.controller.impl.RoleController;
import dk.project.entity.Role;
import dk.project.service.internal.RoleService;
import jakarta.persistence.EntityManagerFactory;

public class RoleRouting extends CRUDRouting<Role> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public RoleRouting(EntityManagerFactory emf) {
        super("/role", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static RoleController createController(EntityManagerFactory emf) {
        return new RoleController(new RoleService(emf.createEntityManager()));
    }

}