package dk.project.service;

import dk.project.dao.UserDAO;
import dk.project.entity.Role;
import dk.project.entity.User;
import jakarta.persistence.EntityManager;
import org.mindrot.jbcrypt.BCrypt;
import java.util.List;
import java.util.UUID;

public class UserService {

    // Attributes
    private final UserDAO userDAO;

    // ____________________________________________________________

    public UserService(EntityManager em){
        this.userDAO = new UserDAO(em);
    }

    // ____________________________________________________________

    public void createUser(User user) {
        // Validation
        validateNotBlank(user.getUsername(), "Username");
        validateNotBlank(user.getEmail(), "Email");
        validateNotBlank(user.getPassword(), "Password");

        // Hash
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String hashedEmail = BCrypt.hashpw(user.getEmail(), BCrypt.gensalt());

        // Set
        user.setPassword(hashedPassword);
        user.setEmail(hashedEmail);

        // Create
        userDAO.createUser(user);
    }

    // ____________________________________________________________

    public User getUserById(UUID userId) {
        return userDAO.getUserById(userId);
    }

    // ____________________________________________________________

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    // ____________________________________________________________

    public void deleteUserById(UUID userId) {
        userDAO.deleteUserById(userId);
    }

    // ____________________________________________________________

    public int deleteAllUsers() {
        return userDAO.deleteAllUsers();
    }

    // ____________________________________________________________

    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }

    // ____________________________________________________________

    public int updateUserColumn(UUID userId, String column, Object value) {
        if ("role".equals(column) && value != null && !(value instanceof Role)) {
            throw new IllegalArgumentException("Value for 'role' skal være et Role objekt");
        }
        return userDAO.updateUserColumn(userId, column, value);
    }

    // ____________________________________________________________

    public void changeUserRole(UUID userId, Role newRole) {
        updateUserColumn(userId, "role", newRole);
    }

    // ____________________________________________________________

    public void updateUsername(UUID userId, String newUsername) {
        validateNotBlank(newUsername, "Username");
        updateUserColumn(userId, "username", newUsername);
    }

    // ____________________________________________________________

    public void updatePassword(UUID userId, String newPassword) {
        validateNotBlank(newPassword, "Password");
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        updateUserColumn(userId, "password", hashedPassword);
    }

    // ____________________________________________________________

    public void updateEmail(UUID userId, String newEmail) {
        validateNotBlank(newEmail, "Email");
        String hashedEmail = BCrypt.hashpw(newEmail, BCrypt.gensalt());
        updateUserColumn(userId, "email", hashedEmail);
    }

    // ____________________________________________________________
    // Validate

    private void validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}