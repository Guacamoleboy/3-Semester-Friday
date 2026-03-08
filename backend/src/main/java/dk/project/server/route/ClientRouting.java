package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class ClientRouting {

    // Attributes
    private final EntityManager em;
    // private final ClientController clientController;

    // _______________________________________________________________________

    public ClientRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        // clientController = new ClientController(em);
    }

    // _______________________________________________________________________

    public EndpointGroup routes() {
        return () -> {
            path("/client", () -> {
                // get("/all", clientController::getAll);
                // get("/{id}", clientController::getById);
                // put("/{id}", clientController::updateById);
                // delete("/{id}", clientController::deleteById);
                // delete("/all", clientController::deleteAll);
            });
        };
    }

}