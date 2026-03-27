package dk.project.route.impl;

import dk.project.controller.auth.ApiController;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class ApiRouting {

    // Attributes
    private final ApiController apiController;

    // _________________________________________________________________________________________________________________

    public ApiRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        this.apiController = new ApiController(em);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {
            path("/access", () -> {
                post("/create", apiController::createApiKey);
                post("/validate", apiController::validateApiKey);
                post("/refresh", apiController::refreshApiKey);
                delete("/delete", apiController::deleteApiKey);
            });
        };

    }

}
