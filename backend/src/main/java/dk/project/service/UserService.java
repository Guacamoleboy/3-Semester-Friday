package dk.project.service;

import dk.project.dao.impl.RoleDAO;
import dk.project.dao.impl.UserDAO;
import dk.project.entity.Role;
import dk.project.entity.User;
import dk.project.exception.ApiException;
import jakarta.persistence.EntityManager;
import org.mindrot.jbcrypt.BCrypt;
import java.util.UUID;

public class UserService extends EntityManagerService<User> {

    // Attributes
    private final RoleDAO roleDAO;

    // _________________________________________________________________________________________________________________

    public UserService(EntityManager em){
        super(new UserDAO(em), User.class);
        this.roleDAO = new RoleDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public User createUser(User user) {
        validateNotEmpty(user.getUsername(), "Username");
        validateNotEmpty(user.getEmail(), "Email");
        validateNotEmpty(user.getPassword(), "Password");

        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setEmail(BCrypt.hashpw(user.getEmail(), BCrypt.gensalt()));

        return super.create(user);
    }

    // _________________________________________________________________________________________________________________

    @Override
    public User update(User user) {
        User existing = entityManagerDAO.getById(user.getId());
        if (existing == null) {
            throw new ApiException(404, "User not found");
        }

        if (user.getUsername() != null) {
            existing.setUsername(user.getUsername());
        }

        if (user.getEmail() != null) {
            String hashedEmail = BCrypt.hashpw(user.getEmail(), BCrypt.gensalt());
            existing.setEmail(hashedEmail);
        }

        return entityManagerDAO.update(existing);
    }

    // _________________________________________________________________________________________________________________

    public void updatePassword(UUID userId, String newPassword) {
        validateNotEmpty(newPassword, "Password");
        super.updateColumnById(userId, "password", BCrypt.hashpw(newPassword, BCrypt.gensalt()));
    }

    // _________________________________________________________________________________________________________________

    public void updateEmail(UUID userId, String newEmail) {
        validateNotEmpty(newEmail, "Email");
        super.updateColumnById(userId, "email", BCrypt.hashpw(newEmail, BCrypt.gensalt()));
    }

    // _________________________________________________________________________________________________________________

    public void updateUsername(UUID userId, String newUsername) {
        validateNotEmpty(newUsername, "Username");
        super.updateColumnById(userId, "username", newUsername);
    }

    // _________________________________________________________________________________________________________________

    public void changeUserRole(UUID userId, Role newRole) {
        super.updateColumnById(userId, "role", newRole);
    }

}