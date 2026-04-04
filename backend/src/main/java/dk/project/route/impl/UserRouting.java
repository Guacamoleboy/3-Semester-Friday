package dk.project.route.impl;

import dk.project.controller.impl.UserController;
import dk.project.enums.AccessLevelEnum;
import dk.project.security.access.AccessValidator;
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

                // -------------------------------------------------------------------

                post("", AccessValidator.access(
                        AccessLevelEnum.PUBLIC,
                        userController::create
                ));

                // -------------------------------------------------------------------

                put("", AccessValidator.access(
                        AccessLevelEnum.JWT,
                        userController::update
                ));

                // -------------------------------------------------------------------

                get("/all", AccessValidator.access(
                        AccessLevelEnum.ADMIN,
                        userController::getAll
                ));

                // -------------------------------------------------------------------

                get("/{id}", AccessValidator.access(
                        AccessLevelEnum.ADMIN,
                        userController::getById
                ));

                // -------------------------------------------------------------------

                delete("/{id}", AccessValidator.access(
                        AccessLevelEnum.ADMIN,
                        userController::deleteById
                ));

                // -------------------------------------------------------------------

                delete("/all", AccessValidator.access(
                        AccessLevelEnum.ADMIN,
                        userController::deleteAll
                ));

                // -------------------------------------------------------------------

                delete("/all/safe", AccessValidator.access(
                        AccessLevelEnum.ADMIN,
                        userController::deleteAllSafe
                ));

            });
        };

    }

    // _________________________________________________________________________________________________________________

}