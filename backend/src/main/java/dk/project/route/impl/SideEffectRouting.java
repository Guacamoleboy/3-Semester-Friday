package dk.project.route.impl;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class SideEffectRouting {

    // Attributes
    private final EntityManager em;
    // private final SideEffectController sideEffectController;

    // _________________________________________________________________________________________________________________

    public SideEffectRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        // sideEffectController = new SideEffectController(em);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {
            path("/sideeffect", () -> {
                // get("/all", sideEffectController::getAll);
                // get("/{id}", sideEffectController::getById);
                // post("/", sideEffectController::create);
                // put("/{id}", sideEffectController::updateById);
                // delete("/{id}", sideEffectController::deleteById);
            });
        };

    }

    // _________________________________________________________________________________________________________________

}