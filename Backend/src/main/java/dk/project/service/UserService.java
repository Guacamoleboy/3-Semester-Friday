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
        validateNotEmpty(user.getUsername(), "Username");
        validateNotEmpty(user.getEmail(), "Email");
        validateNotEmpty(user.getPassword(), "Password");

        // Hash
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String hashedEmail = BCrypt.hashpw(user.getEmail(), BCrypt.gensalt());

        // Set
        user.setPassword(hashedPassword);
        user.setEmail(hashedEmail);

        // Create
        userDAO.create(user);
    }

    // ____________________________________________________________

    public User getUserById(UUID userId) {
        validateNotEmpty(userId, "User.id");
        return userDAO.getById(userId);
    }

    // ____________________________________________________________

    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    // ____________________________________________________________

    public void deleteUserById(UUID userId) {
        validateNotEmpty(userId, "User.id");
        userDAO.deleteById(userId);
    }

    // ____________________________________________________________

    public void deleteAllUsers() {
        userDAO.deleteAll();
    }

    // ____________________________________________________________

    public User updateUser(User user) {
        return userDAO.update(user);
    }

    // ____________________________________________________________

    public Role getRoleForUser(UUID userId){
        validateNotEmpty(userId, "User.id");
        return userDAO.getRoleForUser(userId);
    }

    // ____________________________________________________________

    public int updateUserColumn(UUID userId, String column, Object value) {
        validateNotEmpty(userId, "User.id");

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
        validateNotEmpty(newUsername, "Username");
        updateUserColumn(userId, "username", newUsername);
    }

    // ____________________________________________________________

    public void updatePassword(UUID userId, String newPassword) {
        validateNotEmpty(newPassword, "Password");
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        updateUserColumn(userId, "password", hashedPassword);
    }

    // ____________________________________________________________

    public void updateEmail(UUID userId, String newEmail) {
        validateNotEmpty(newEmail, "Email");
        String hashedEmail = BCrypt.hashpw(newEmail, BCrypt.gensalt());
        updateUserColumn(userId, "email", hashedEmail);
    }

    // ____________________________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}