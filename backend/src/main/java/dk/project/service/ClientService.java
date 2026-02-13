package dk.project.service;

import dk.project.dao.ClientDAO;
import dk.project.entity.Client;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ClientService {

    // Attributes
    private final ClientDAO clientDAO;

    // _________________________________________________

    public ClientService(EntityManager em){
        this.clientDAO = new ClientDAO(em);
    }

    // _________________________________________________

    public void createClient(Client client){
        validateNotEmpty(client.getId(), "Client.id");
        clientDAO.create(client);
    }

    // _________________________________________________

    public void updateClient(Client client){
        validateNotEmpty(client.getId(), "Client.id");
        clientDAO.update(client);
    }

    // _________________________________________________

    public void deleteClient(String clientId){
        validateNotEmpty(clientId, "Client.id");
        clientDAO.deleteById(clientId);
    }

    // _________________________________________________

    public void deleteAllClients(){
        clientDAO.deleteAll();
    }

    // _________________________________________________

    public Client getClientById(String clientId){
        validateNotEmpty(clientId, "Client.id");
        return clientDAO.getById(clientId);
    }

    // _________________________________________________

    public String getIdEndingById(String clientId){
        validateNotEmpty(clientId, "Client.id");
        return clientDAO.getIdEndingById(clientId);
    }

    // _________________________________________________

    public List<Client> getAllClients(){
        return clientDAO.getAll();
    }

    // _________________________________________________

    public boolean existsById(String clientId){
        validateNotEmpty(clientId, "Client.id");
        return clientDAO.existsById(clientId);
    }

    // _________________________________________________

    private void validateNotEmpty(Object paramValue, String fieldName) {
        if (paramValue == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (paramValue instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}
