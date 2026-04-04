package dk.project.route.status;

import dk.project.controller.status.StatusController;
import dk.project.enums.AccessLevelEnum;
import dk.project.security.access.AccessValidator;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class StatusRouting {

    // Attributes
    private final StatusController statusController;

    // _________________________________________________________________________________________________________________

    public StatusRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        this.statusController = new StatusController(em);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {

            // Route Endpoints
            path("/status", () -> {

                // -------------------------------------------------------------------

                post("", AccessValidator.access(
                        AccessLevelEnum.PUBLIC,
                        statusController::getStatus
                ));

            });

        };

    }

}