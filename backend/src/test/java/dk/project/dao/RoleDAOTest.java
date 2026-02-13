package dk.project.dao;

import dk.project.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RoleDAOTest extends ADAOTest {

    // Attributes
    private RoleDAO roleDAO;

    // _____________________________________________________

    @BeforeEach
    public void setupDAO() {
        roleDAO = new RoleDAO(em);
        roleDAO.deleteAll();
    }

    // _____________________________________________________

    @Test
    public void shouldFindRoleByName() {
        // Arrange
        Role adminRole = new Role();
        adminRole.setName("Admin");
        adminRole.setDescription("Administrator role");
        roleDAO.create(adminRole);

        Role userRole = new Role();
        userRole.setName("User");
        userRole.setDescription("Standard user");
        roleDAO.create(userRole);

        // Act
        Role retrievedRole = roleDAO.findByName("Admin");

        // Assert
        assertNotNull(retrievedRole);
        assertEquals(adminRole.getId(), retrievedRole.getId());
        assertEquals("Admin", retrievedRole.getName());
        assertEquals("Administrator role", retrievedRole.getDescription());
    }

    // _____________________________________________________

    @Test
    public void shouldReturnTrueIfRoleExistsByName() {
        // Arrange
        Role role = new Role();
        role.setName("Moderator");
        role.setDescription("Moderator role");
        roleDAO.create(role);

        // Act
        boolean exists = roleDAO.existsByName("Moderator");

        // Assert
        assertTrue(exists);
    }

    // _____________________________________________________

    @Test
    public void shouldReturnFalseIfRoleDoesNotExistByName() {
        // Arrange
        Role role = new Role();
        role.setName("Editor");
        role.setDescription("Editor role");
        roleDAO.create(role);

        // Act
        boolean exists = roleDAO.existsByName("NonExistentRole");

        // Assert
        assertFalse(exists);
    }

}