package dk.project.controller.impl;

import dk.project.entity.Client;
import dk.project.service.internal.EntityManagerService;

public class ClientController extends CRUDController<Client> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public ClientController(EntityManagerService<Client> service) {
        super(service, Client.class, ClientResponseMapper::toDTO);
    }

}
