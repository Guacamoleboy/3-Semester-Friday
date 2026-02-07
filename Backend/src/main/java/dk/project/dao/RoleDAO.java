package dk.project.dao;

import dk.project.entity.Role;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class RoleDAO extends EntityManagerDAO {

    // Attributes

    // ________________________________________

    public RoleDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createRole(Role role){
        executeQuery(() -> em.persist(role));
    }

    // ________________________________________

    public void updateRole(Role role) {
        executeQuery(() -> em.merge(role));
    }

    // ________________________________________

    public Role getRoleById(UUID roleId) {
        return executeQuery(() -> em.find(Role.class, roleId));
    }

    // ________________________________________

    public void deleteRole(UUID roleId) {
        executeQuery(() -> {
            Role role = em.find(Role.class, roleId);
            if (role != null) {
                em.remove(role);
            }
        });
    }

    // ________________________________________

    public int deleteAllRoles() {
        return executeQuery(() ->
            em.createQuery("DELETE FROM Role x")
            .executeUpdate()
        );
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

    public Role findByName(String name) {
        return executeQuery(() -> em.createQuery(
            "SELECT x FROM Role x WHERE x.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult()
        );
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
    // COUNT() -> Long. Not sure why.

    public boolean existsByName(String name) {
        return executeQuery(() -> {
            Long count = em.createQuery(
        "SELECT COUNT(x) FROM Role x WHERE x.name = :name", Long.class)
            .setParameter("name", name)
            .getSingleResult();
            return count > 0;
        });
    }

    // ________________________________________

    public List<Role> getAllRoles(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Role x";
            return em.createQuery(JPQL, Role.class)
            .getResultList();
        });
    }


}