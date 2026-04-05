package dk.project.route.impl;

import dk.project.controller.impl.UserController;
import dk.project.entity.User;
import dk.project.service.internal.UserService;
import jakarta.persistence.EntityManagerFactory;

public class UserRouting extends CRUDRouting<User> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public UserRouting(EntityManagerFactory emf) {
        super("/user", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static UserController createController(EntityManagerFactory emf) {
        return new UserController(new UserService(emf.createEntityManager()));
    }

}