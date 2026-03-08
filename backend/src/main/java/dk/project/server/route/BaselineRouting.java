package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class BaselineRouting {

    // Attributes
    private final EntityManager em;
    // private final BaselineController baselineController;
    // private final BaselineIndividualController baselineIndividualController;

    // ____________________________________________________________________________________

    public BaselineRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        // baselineController = new BaselineController(em);
        // baselineIndividualController = new BaselineIndividualController(em);
    }

    // ____________________________________________________________________________________

    public EndpointGroup routes() {
        return () -> {
            path("/baseline", () -> {
                // get("/all", baselineController::getAll);
                // get("/{id}", baselineController::getById);
                // post("/", baselineController::create);
                // put("/{id}", baselineController::updateById);
                // delete("/{id}", baselineController::deleteById);

                // Individual
                path("/individual", () -> {
                    // get("/all", baselineIndividualController::getAll);
                    // get("/{id}", baselineIndividualController::getById);
                    // post("/", baselineIndividualController::create);
                    // put("/{id}", baselineIndividualController::updateById);
                    // delete("/{id}", baselineIndividualController::deleteById);

                    // Sideeffects for baseline/individual
                    path("/sideeffect", () -> {
                        // get("/all", baselineIndividualController::getSideEffects);
                        // post("/", baselineIndividualController::addSideEffect);
                        // delete("/{id}", baselineIndividualController::removeSideEffect);
                    });

                });

            });

        };
    }

}