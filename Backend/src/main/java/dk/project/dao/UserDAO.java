package dk.project.dao;

import dk.project.entity.Role;
import dk.project.entity.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class UserDAO extends EntityManagerDAO {

    // Attributes

    // ________________________________________________________________________

    public UserDAO(EntityManager em){
        super(em);
    }

    // ________________________________________________________________________

    public void createUser(User user) {
        executeQuery(() -> em.persist(user));
    }

    // ________________________________________________________________________

    public User getUserById(UUID id) {
        return executeQuery(() -> em.find(User.class, id));
    }

    // ________________________________________________________________________
    // Set up with {} lamba (multi lin lambda) for testing purposes

    public List<User> getAllUsers() {
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM User x";
            return em.createQuery(JPQL, User.class)
            .getResultList();
        });
    }

    // ________________________________________________________________________

    public void deleteUserById(UUID id) {
        executeQuery(() -> {
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
        });
    }

    // ________________________________________________________________________

    public int deleteAllUsers() {
        return executeQuery(() ->
            em.createQuery("DELETE FROM User")
            .executeUpdate()
        );
    }

    // ________________________________________________________________________

    public User updateUser(User user) {
        return executeQuery(() -> em.merge(user));
    }

    // ________________________________________________________________________

    public Role getRoleForUser(UUID userId) {
        return executeQuery(() -> {
            User user = em.find(User.class, userId);
            return user.getRole();
        });
    }

    // ________________________________________________________________________
    // Generic update method for a single column

    public int updateUserColumn(UUID userId, String column, Object value) {

        // Validation
        List<String> allowed = List.of("username", "email", "password", "lastLogin", "role");
        if (!allowed.contains(column)) {
            throw new IllegalArgumentException("Ugyldigt kolonnenavn: " + column);
        }

        // Query
        return executeQuery(() -> {
            String JPQL = "UPDATE User x SET x." + column + " = :value WHERE x.id = :id";
            return em.createQuery(JPQL)
                .setParameter("value", value)
                .setParameter("id", userId)
                .executeUpdate();
        });

    }

}