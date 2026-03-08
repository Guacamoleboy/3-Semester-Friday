package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class SideEffectRouting {

    // Attributes
    private final EntityManager em;
    // private final SideEffectController sideEffectController;

    // _______________________________________________________________________________

    public SideEffectRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        // sideEffectController = new SideEffectController(em);
    }

    // _______________________________________________________________________________

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

    // _______________________________________________________________________________

}