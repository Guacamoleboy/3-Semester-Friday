package dk.project.dao;

import dk.project.entity.Client;
import jakarta.persistence.EntityManager;
import java.util.List;

public class ClientDAO extends EntityManagerDAO {

    // Attributes

    // ________________________________________

    public ClientDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createClient(Client client){
        executeQuery(() -> em.persist(client));
    }

    // ________________________________________

    public void updateClient(Client client){
        executeQuery(() -> em.merge(client));
    }

    // ________________________________________

    public void deleteClient(String clientId){
        executeQuery(() -> {
            Client client = em.find(Client.class, clientId);
            if (client != null) em.remove(client);
        });
    }

    // ________________________________________

    public int deleteAllClients(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM Client x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public Client getClientById(String clientId){
        return executeQuery(() -> em.find(Client.class, clientId));
    }

    // ________________________________________

    public String getIdEndingById(String clientId){
        return executeQuery(() -> {
            String JPQL = "SELECT x.idEnding FROM Client x WHERE x.id = :id";
            return em.createQuery(JPQL, String.class)
            .setParameter("id", clientId)
            .getSingleResult();
        });
    }

    // ________________________________________

    public List<Client> getAllClients(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Client x";
            return em.createQuery(JPQL, Client.class)
            .getResultList();
        });
    }

    // ________________________________________

    public boolean existsById(String clientId){
        return executeQuery(() -> {
            Long count = em.createQuery(
        "SELECT COUNT(x) FROM Client x WHERE x.id = :id", Long.class)
            .setParameter("id", clientId)
            .getSingleResult();
            return count > 0;
        });
    }

}