package dk.project.dao;

import dk.project.entity.Role;
import dk.project.entity.User;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class UserDAO extends EntityManagerDAO<User> {

    // Attributes

    // ________________________________________

    public UserDAO(EntityManager em){
        super(em, User.class);
    }

    // ________________________________________

    public Role getRoleForUser(UUID userId) {
        return executeQuery(() -> {
            User user = em.find(User.class, userId);
            return user.getRole();
        });
    }

    // ________________________________________

    public int updateUserColumn(UUID userId, String column, Object value) {

        List<String> allowed = List.of("username", "email", "password", "lastLogin", "role");
        if (!allowed.contains(column)) {
            throw new IllegalArgumentException("Ugyldigt kolonnenavn: " + column);
        }

        return executeQuery(() -> {
            String JPQL = "UPDATE User x SET x." + column + " = :value WHERE x.id = :id";
            return em.createQuery(JPQL)
                    .setParameter("value", value)
                    .setParameter("id", userId)
                    .executeUpdate();
        });

    }

}