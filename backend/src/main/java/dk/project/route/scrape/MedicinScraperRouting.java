package dk.project.route.scrape;

import dk.project.controller.scrape.MedicinScraperController;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class MedicinScraperRouting {

    // Attributes
    private final MedicinScraperController medicinScraperController;

    // _________________________________________________________________________________________________________________

    public MedicinScraperRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        this.medicinScraperController = new MedicinScraperController(em);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {
            path("/scrape/medicin", () -> {
                post("/{id}", medicinScraperController::getSideEffects);
            });
        };

    }

}
