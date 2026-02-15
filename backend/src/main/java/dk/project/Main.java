package dk.project;

import dk.project.entity.Client;
import dk.project.service.ClientService;
import dk.project.config.HibernateConfig;
import dk.project.util.PopulateDB;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {

    // Attributes

    // _________________________________________________________________

    public static void main(String[] args) {

        // Initial
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        // Try-with-resource
        try (EntityManager em = emf.createEntityManager()) {

            // Service Test
            ClientService clientService = new ClientService(em);

            em.getTransaction().begin();
            //PopulateDB.populate(em);

            // Get All Clients
            List<Client> allClients = clientService.getAll();
            System.out.println("All clients:");
            allClients.forEach(c -> System.out.println(
                    "Client hash: " + c.getId() +
                    ", idEnding: " + c.getIdEnding() +
                    ", createdAt: " + c.getCreatedAt()
            ));

            // Client actions
            if (!allClients.isEmpty()) {

                // Initial
                Client firstClient = allClients.get(0);
                Client foundClient = clientService.getById(firstClient.getId());
                System.out.println("\nFound client by ID:");
                System.out.println("Client hash: " + foundClient.getId());

                // idEnding
                Integer ending = clientService.getColumnById(firstClient.getId(), "idEnding");
                System.out.println("getIdEndingById: " + ending);

                // Update
                foundClient.setIdEnding(foundClient.getIdEnding() + 1);
                clientService.update(foundClient);
                System.out.println("Updated client idEnding to: " + foundClient.getIdEnding());

                // Exist
                boolean exists = clientService.existsById(foundClient.getId());
                System.out.println("Does client exist? " + exists);

            }

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }

    }

}