package dk.project.dao.impl;

import dk.project.entity.Role;
import dk.project.entity.User;
import jakarta.persistence.EntityManager;
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

}