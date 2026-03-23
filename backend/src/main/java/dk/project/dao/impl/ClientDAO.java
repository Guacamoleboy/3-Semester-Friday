package dk.project.dao.impl;

import dk.project.entity.Client;
import jakarta.persistence.EntityManager;

public class ClientDAO extends EntityManagerDAO<Client> {

    // Attributes

    // ________________________________________

    public ClientDAO(EntityManager em) {
        super(em, Client.class);
    }

    // ________________________________________

    public Integer getIdEndingById(String clientId) {
        return executeQuery(() -> {
            String JPQL = "SELECT x.idEnding FROM Client x WHERE x.id = :id";
            return em.createQuery(JPQL, Integer.class)
            .setParameter("id", clientId)
            .getSingleResult();
        });
    }

    // ________________________________________

    public boolean existsById(String clientId) {
        return executeQuery(() -> {
            Long count = em.createQuery(
        "SELECT COUNT(x) FROM Client x WHERE x.id = :id", Long.class)
            .setParameter("id", clientId)
            .getSingleResult();
            return count > 0;
        });
    }

}