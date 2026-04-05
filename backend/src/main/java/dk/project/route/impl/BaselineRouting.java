package dk.project.route.impl;

import dk.project.controller.impl.BaselineController;
import dk.project.controller.impl.BaselineIndividualController;
import dk.project.service.internal.BaselineIndividualService;
import dk.project.service.internal.BaselineService;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class BaselineRouting {

    // Attributes
    private final BaselineController baselineController;
    private final BaselineIndividualController baselineIndividualController;

    // _________________________________________________________________________________________________________________

    public BaselineRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        BaselineService baselineService = new BaselineService(em);
        baselineController = new BaselineController(baselineService);

        BaselineIndividualService baselineIndividualService = new BaselineIndividualService(em);
        baselineIndividualController = new BaselineIndividualController(baselineIndividualService);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {
        return () -> {

            path("/baseline", () -> {

                // -------------------------------------------------------------------

                get("/all", baselineController::getAll);

                // -------------------------------------------------------------------

                get("/{id}", baselineController::getById);

                // -------------------------------------------------------------------

                post("/", baselineController::create);

                // -------------------------------------------------------------------

                put("/{id}", baselineController::updateById);

                // -------------------------------------------------------------------

                delete("/{id}", baselineController::deleteById);

                // -------------------------------------------------------------------

                path("/individual", () -> {

                    // -------------------------------------------------------------------

                    get("/all", baselineIndividualController::getAll);

                    // -------------------------------------------------------------------

                    get("/{id}", baselineIndividualController::getById);

                    // -------------------------------------------------------------------

                    post("/", baselineIndividualController::create);

                    // -------------------------------------------------------------------

                    put("/{id}", baselineIndividualController::updateById);

                    // -------------------------------------------------------------------

                    delete("/{id}", baselineIndividualController::deleteById);

                    // -------------------------------------------------------------------

                    path("/sideeffect", () -> {

                        // -------------------------------------------------------------------

                        get("/all", baselineIndividualController::getSideEffects);

                        // -------------------------------------------------------------------

                        post("/", baselineIndividualController::addSideEffect);

                        // -------------------------------------------------------------------

                        delete("/{id}", baselineIndividualController::removeSideEffect);

                    });

                });

            });

        };
    }

}