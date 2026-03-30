package dk.project.route.data;

import dk.project.controller.data.PopulateController;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.post;

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
                post("", populateController::populate);
            });
        };

    }



}
