package dk.project.route.health;

import dk.project.controller.health.HealthController;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class HealthRouting {

    // Attributes
    private final HealthController healthController;

    // _________________________________________________________________________________________________________________

    public HealthRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        this.healthController = new HealthController(em);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {
            path("/health", () -> {
                post("", healthController::getHealth);
            });
        };

    }

}