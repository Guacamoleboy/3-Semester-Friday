package dk.project.dao;

import dk.project.entity.Role;
import jakarta.persistence.EntityManager;
import java.util.UUID;

public class RoleDAO extends EntityManagerDAO<Role> {

    // Attributes

    // ________________________________________

    public RoleDAO(EntityManager em){
        super(em, Role.class);
    }

    // ________________________________________

    public String getNameById(UUID roleId){
        return executeQuery(() -> {
            String JPQL = "SELECT x.name FROM Role x WHERE x.id = :id";
            return em.createQuery(JPQL, String.class)
                    .setParameter("id", roleId)
                    .getSingleResult();
        });
    }

    // ________________________________________

    public String getDescriptionById(UUID roleId){
        return executeQuery(() -> {
            String JPQL = "SELECT x.description FROM Role x WHERE x.id = :id";
            return em.createQuery(JPQL, String.class)
                    .setParameter("id", roleId)
                    .getSingleResult();
        });
    }

    // ________________________________________

    public Role findByName(String name) {
        return executeQuery(() -> em.createQuery(
                        "SELECT x FROM Role x WHERE x.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult()
        );
    }

    // ________________________________________

    public boolean existsByName(String name) {
        return executeQuery(() -> {
            Long count = em.createQuery(
                            "SELECT COUNT(x) FROM Role x WHERE x.name = :name", Long.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return count > 0;
        });
    }

}