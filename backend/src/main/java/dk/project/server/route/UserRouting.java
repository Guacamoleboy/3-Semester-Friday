package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.path;

public class UserRouting {

    // Attributes
    private final EntityManager em;
    // private final UserController userController;

    // _______________________________________________________________________________

    public UserRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        // userController = new UserController(em);
    }

    // _______________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {
            path("/user", () -> {
                // get("/all", userController::getAll);
                // delete("/all", userController::deleteAll);
                // get("/{id}", userController::getById);
                // put("/{id}", userController::updateById);
                // delete("/{id}", userController::deleteById);
            });
        };

    }

    // _______________________________________________________________________________

}