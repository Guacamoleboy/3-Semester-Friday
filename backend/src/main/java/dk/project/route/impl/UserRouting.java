package dk.project.route.impl;

import dk.project.controller.impl.UserController;
import dk.project.service.internal.UserService;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.*;

public class UserRouting {

    // Attributes
    private final UserController userController;

    // _________________________________________________________________________________________________________________

    public UserRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        UserService userService = new UserService(em);
        userController = new UserController(userService);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {
            path("/user", () -> {
                post("", userController::create);
                put("", userController::update);
                get("/all", userController::getAll);
                get("/{id}", userController::getById);
                delete("/{id}", userController::deleteById);
                delete("/all", userController::deleteAll);
                delete("/all/safe", userController::deleteAllSafe);
            });
        };

    }

    // _________________________________________________________________________________________________________________

}