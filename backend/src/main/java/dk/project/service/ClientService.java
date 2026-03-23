package dk.project.service;

import dk.project.dao.impl.ClientDAO;
import dk.project.entity.Client;
import jakarta.persistence.EntityManager;
import org.mindrot.jbcrypt.BCrypt;

public class ClientService extends EntityManagerService<Client> {

    // Attributes
    private final ClientDAO clientDAO;

    // _________________________________________________

    public ClientService(EntityManager em){
        super(new ClientDAO(em), Client.class);
        this.clientDAO = (ClientDAO) this.entityManagerDAO;
    }

    // _________________________________________________

    public Client createClient(Client client){
        validateNotEmpty(client.getId(), "Client.id");
        String hashedId = BCrypt.hashpw(client.getId(), BCrypt.gensalt());
        client.setId(hashedId);
        return super.create(client);
    }

    // _________________________________________________

    public boolean existsById(String clientId){
        validateNotEmpty(clientId, "Client.id");
        return clientDAO.existsById(clientId);
    }

}