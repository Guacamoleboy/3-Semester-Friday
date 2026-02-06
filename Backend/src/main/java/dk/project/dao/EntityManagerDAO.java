//
//  Protected to limit usage to only subclasses (Direct extend from this DAO)
//  - Guac
//

package dk.project.dao;

import jakarta.persistence.EntityManager;
import java.util.function.Supplier;

public class EntityManagerDAO {

    // Attributes
    protected EntityManager em;

    // ________________________________________________________

    protected EntityManagerDAO(EntityManager em){
        this.em = em;
    }

    // ________________________________________________________
    // Unknown object type query execute using Supplier from java.util

    protected <T> T executeQuery(Supplier<T> query) {
        try {
            // begin()
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            // Query (JPA / JPQL)
            T result = query.get();
            // commit()
            em.getTransaction().commit();
            // Return T
            return result;
        } catch (RuntimeException e) {
            // Fallback
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    // ________________________________________________________
    // void execute

    protected void executeQuery(Runnable task) {
        executeQuery(() -> {
            task.run();
            return null;
        });
    }

}