package dk.project.service;

import dk.project.dao.UserDAO;
import dk.project.entity.Role;
import dk.project.entity.User;
import jakarta.persistence.EntityManager;
import org.mindrot.jbcrypt.BCrypt;
import java.util.UUID;

public class UserService extends EntityManagerService<User> {

    // Attributes

    // _________________________________________________

    public UserService(EntityManager em){
        super(new UserDAO(em), User.class);
    }

    // _________________________________________________

    public User createUser(User user) {
        validateNotEmpty(user.getUsername(), "Username");
        validateNotEmpty(user.getEmail(), "Email");
        validateNotEmpty(user.getPassword(), "Password");

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setEmail(BCrypt.hashpw(user.getEmail(), BCrypt.gensalt()));

        return super.create(user);
    }

    // _________________________________________________

    public void updatePassword(UUID userId, String newPassword) {
        validateNotEmpty(newPassword, "Password");
        super.updateColumnById(userId, "password", BCrypt.hashpw(newPassword, BCrypt.gensalt()));
    }

    // _________________________________________________

    public void updateEmail(UUID userId, String newEmail) {
        validateNotEmpty(newEmail, "Email");
        super.updateColumnById(userId, "email", BCrypt.hashpw(newEmail, BCrypt.gensalt()));
    }

    // _________________________________________________

    public void updateUsername(UUID userId, String newUsername) {
        validateNotEmpty(newUsername, "Username");
        super.updateColumnById(userId, "username", newUsername);
    }

    // _________________________________________________

    public void changeUserRole(UUID userId, Role newRole) {
        super.updateColumnById(userId, "role", newRole);
    }

}