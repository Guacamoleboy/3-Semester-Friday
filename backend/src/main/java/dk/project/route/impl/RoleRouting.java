package dk.project.route.impl;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.path;

public class RoleRouting {

    // Attributes
    private final EntityManager em;
    // private final RoleController roleController;

    // _______________________________________________________________________________

    public RoleRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        // roleController = new RoleController(em);
    }

    // _______________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {
            path("/role", () -> {
                // get("/all", roleController::getAll);
                // get("/{id}", roleController::getById);
                // put("/{id}", roleController::updateById);
                // delete("/{id}", roleController::deleteById);
                // delete("/all", roleController::deleteAll);
            });
        };

    }

    // _______________________________________________________________________________

}