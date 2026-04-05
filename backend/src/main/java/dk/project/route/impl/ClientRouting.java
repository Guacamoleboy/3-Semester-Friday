package dk.project.route.impl;

import dk.project.controller.impl.ClientController;
import dk.project.service.internal.ClientService;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class ClientRouting {

    // Attributes
    private final EntityManager em;
    private final ClientController clientController;

    // _________________________________________________________________________________________________________________

    public ClientRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        ClientService clientService = new ClientService(em);
        clientController = new ClientController(clientService);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {
        return () -> {
            path("/client", () -> {

                // -------------------------------------------------------------------

                get("/all", clientController::getAll);

                // -------------------------------------------------------------------

                get("/{id}", clientController::getById);

                // -------------------------------------------------------------------

                put("/{id}", clientController::updateById);

                // -------------------------------------------------------------------

                delete("/{id}", clientController::deleteById);

                // -------------------------------------------------------------------

                delete("/all", clientController::deleteAll);

            });

        };
    }

}