package dk.project.route.impl;

import dk.project.controller.impl.ClientController;
import dk.project.entity.Client;
import dk.project.service.internal.ClientService;
import jakarta.persistence.EntityManagerFactory;

public class ClientRouting extends CRUDRouting<Client> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public ClientRouting(EntityManagerFactory emf) {
        super("/client", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static ClientController createController(EntityManagerFactory emf) {
        return new ClientController(new ClientService(emf.createEntityManager()));
    }

}