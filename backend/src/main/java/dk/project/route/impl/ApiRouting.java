package dk.project.route.impl;

import dk.project.controller.auth.ApiController;
import dk.project.enums.AccessLevelEnum;
import dk.project.security.access.AccessValidator;
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

                // -------------------------------------------------------------------

                get("/{keyId}", AccessValidator.access(
                        AccessLevelEnum.JWT_AND_API,
                        apiController::getApiMeta
                ));

                // -------------------------------------------------------------------

                post("/create", AccessValidator.access(
                        AccessLevelEnum.JWT,
                        apiController::createApiKey
                ));

                // -------------------------------------------------------------------

                post("/validate", AccessValidator.access(
                        AccessLevelEnum.JWT,
                        apiController::validateApiKey
                ));

                // -------------------------------------------------------------------

                post("/refresh", AccessValidator.access(
                        AccessLevelEnum.JWT_AND_API,
                        apiController::refreshApiKey
                ));

                // -------------------------------------------------------------------

                delete("/delete", AccessValidator.access(
                        AccessLevelEnum.JWT_AND_API,
                        apiController::deleteApiKey
                ));


            });

        };

    }

}
