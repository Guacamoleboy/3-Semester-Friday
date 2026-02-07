package dk.project.service;

import dk.project.dao.ClientDAO;
import jakarta.persistence.EntityManager;

public class ClientService {

    // Attributes

    private final ClientDAO clientDAO;

    // _________________________________________________

    public ClientService(EntityManager em){
        this.clientDAO = new ClientDAO(em);
    }

    // _________________________________________________



}