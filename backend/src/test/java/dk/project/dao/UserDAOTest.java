package dk.project.dao;

import dk.project.entity.Role;
import dk.project.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDAOTest extends ADAOTest {

    // Attributes
    private UserDAO userDAO;
    private EntityManagerDAO<Role> roleDAO;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        userDAO = new UserDAO(em);
        roleDAO = new EntityManagerDAO<>(em, Role.class);
        userDAO.deleteAll();
        roleDAO.deleteAll();
    }

    // _____________________________________________________

    @Test
    public void shouldGetRoleForUser() {
        // Arrange
        Role role = new Role();
        role.setName("User");
        roleDAO.create(role);
        User user = new User();
        user.setUsername("Jonas");
        user.setEmail("jonas@mail.dk");
        user.setPassword("password123");
        user.setRole(role);
        userDAO.create(user);

        // Act
        Role retrievedRole = userDAO.getRoleForUser(user.getId());

        // Assert
        assertNotNull(retrievedRole);
        assertEquals(role.getId(), retrievedRole.getId());
        assertEquals("User", retrievedRole.getName());
    }

    // _____________________________________________________

    @Test
    public void shouldUpdateUserColumn() {
        // Arrange
        Role role = new Role();
        role.setName("Admin");
        roleDAO.create(role);

        User user = new User();
        user.setUsername("Jonas");
        user.setEmail("jonas@mail.dk");
        user.setPassword("password123");
        user.setRole(role);
        userDAO.create(user);

        // Act
        int updatedCount = userDAO.updateColumnById(user.getId(), "username", "Andreas");
        em.clear();
        User updatedUser = userDAO.getById(user.getId());

        // Assert
        assertEquals(1, updatedCount);
        assertEquals("Andreas", updatedUser.getUsername());
    }

    // _____________________________________________________

    @Test
    public void shouldThrowExceptionForInvalidColumn() {
        // Arrange
        Role role = new Role();
        role.setName("TestRole");
        roleDAO.create(role);

        User user = new User();
        user.setUsername("carol");
        user.setEmail("carol@example.com");
        user.setPassword("pass");
        user.setRole(role);
        userDAO.create(user);

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            userDAO.updateColumnById(user.getId(), "invalidColumn", "value")
        );

        // Assert
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Invalid column"));
    }

}