package dk.project.route.data;

import dk.project.controller.data.PopulateController;
import dk.project.enums.AccessLevelEnum;
import dk.project.security.access.AccessValidator;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class PopulateRouting {

    // Attributes
    private final PopulateController populateController;

    // _________________________________________________________________________________________________________________

    public PopulateRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        this.populateController = new PopulateController(em);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {

            path("/data/populate", () -> {

                // -------------------------------------------------------------------

                post("", AccessValidator.access(
                        populateController::populate,
                        AccessLevelEnum.PUBLIC
                ));

            });

        };

    }

}