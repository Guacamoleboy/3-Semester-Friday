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
        clientDAO.createClient(client);
    }

    // _________________________________________________

    public void updateClient(Client client){
        validateNotEmpty(client.getId(), "Client.id");
        clientDAO.updateClient(client);
    }

    // _________________________________________________

    public void deleteClient(String clientId){
        validateNotEmpty(clientId, "Client.id");
        clientDAO.deleteClient(clientId);
    }

    // _________________________________________________

    public int deleteAllClients(){
        return clientDAO.deleteAllClients();
    }

    // _________________________________________________

    public Client getClientById(String clientId){
        validateNotEmpty(clientId, "Client.id");
        return clientDAO.getClientById(clientId);
    }

    // _________________________________________________

    public String getIdEndingById(String clientId){
        validateNotEmpty(clientId, "Client.id");
        return clientDAO.getIdEndingById(clientId);
    }

    // _________________________________________________

    public List<Client> getAllClients(){
        List<Client> clients = clientDAO.getAllClients();
        return clients;
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