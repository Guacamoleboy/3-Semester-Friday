package dk.project.dao;

import dk.project.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntityManagerDAOTest extends ADAOTest {

    // Attributes
    private EntityManagerDAO<Role> entityManagerDAO;

    // ____________________________________________________

    @BeforeEach
    public void setupDAO() {
        this.entityManagerDAO = new EntityManagerDAO<>(em, Role.class);
        entityManagerDAO.deleteAll();
    }

    // ____________________________________________________

    @Test
    public void shouldCreateRole() {
        // Arrange
        Role role = new Role();
        role.setName("Admin");
        role.setDescription("Administrator role");

        // Act
        Role createdRole = entityManagerDAO.create(role);

        // Assert
        assertNotNull(createdRole);
        assertNotNull(createdRole.getId());
        assertEquals("Admin", createdRole.getName());
        assertEquals("Administrator role", createdRole.getDescription());
    }

    // ____________________________________________________

    @Test
    public void shouldGetById() {
        // Arrange
        Role role = new Role();
        role.setName("User");
        role.setDescription("Standard user role");
        entityManagerDAO.create(role);

        // Act
        Role retrievedRole = entityManagerDAO.getById(role.getId());

        // Assert
        assertNotNull(retrievedRole);
        assertEquals(role.getId(), retrievedRole.getId());
        assertEquals("User", retrievedRole.getName());
        assertEquals("Standard user role", retrievedRole.getDescription());
    }

    // ____________________________________________________

    @Test
    public void shouldUpdateRole() {
        // Arrange
        Role role = new Role();
        role.setName("Moderator");
        role.setDescription("Moderator role");
        entityManagerDAO.create(role);

        // Act
        role.setDescription("Updated moderator role");
        Role updatedRole = entityManagerDAO.update(role);

        // Assert
        assertNotNull(updatedRole);
        assertEquals(role.getId(), updatedRole.getId());
        assertEquals("Moderator", updatedRole.getName());
        assertEquals("Updated moderator role", updatedRole.getDescription());
    }

    // ____________________________________________________

    @Test
    public void shouldDeleteRole() {
        // Arrange
        Role role = new Role();
        role.setName("Guest");
        role.setDescription("Guest role");
        entityManagerDAO.create(role);

        // Act
        Role deletedRole = entityManagerDAO.delete(role);
        Role retrievedAfterDelete = entityManagerDAO.getById(role.getId());

        // Assert
        assertNotNull(deletedRole);
        assertNull(retrievedAfterDelete);
    }

    // ____________________________________________________

    @Test
    public void shouldGetAllRoles() {
        // Arrange
        Role role1 = new Role();
        role1.setName("Admin");
        role1.setDescription("Administrator");
        entityManagerDAO.create(role1);

        Role role2 = new Role();
        role2.setName("User");
        role2.setDescription("Standard user");
        entityManagerDAO.create(role2);

        // Act
        List<Role> allRoles = entityManagerDAO.getAll();

        // Assert
        assertEquals(2, allRoles.size());
        assertTrue(allRoles.stream().anyMatch(r -> r.getName().equals("Admin")));
        assertTrue(allRoles.stream().anyMatch(r -> r.getName().equals("User")));
    }

    // ____________________________________________________

    @Test
    public void shouldGetColumnById() {
        // Arrange
        Role role = new Role();
        role.setName("Column");
        role.setDescription("Test description");
        entityManagerDAO.create(role);

        // Act
        String retrievedName = entityManagerDAO.getColumnById(role.getId(), "name");
        String retrievedDescription = entityManagerDAO.getColumnById(role.getId(), "description");

        // Assert
        assertNotNull(retrievedName);
        assertNotNull(retrievedDescription);
        assertEquals("Column", retrievedName);
        assertEquals("Test description", retrievedDescription);
    }

    // ____________________________________________________

    @Test
    public void shouldDeleteById() {
        // Arrange
        Role role = new Role();
        role.setName("Temp");
        role.setDescription("Temporary role");
        entityManagerDAO.create(role);

        // Act
        Role deletedById = entityManagerDAO.deleteById(role.getId());
        Role retrievedAfterDelete = entityManagerDAO.getById(role.getId());

        // Assert
        assertNotNull(deletedById);
        assertNull(retrievedAfterDelete);
    }

    // ____________________________________________________

    @Test
    public void shouldExecuteQuerySupplier() {
        // Arrange
        Role role = new Role();
        role.setName("Tester");
        role.setDescription("Test role");
        entityManagerDAO.create(role);

        // Act
        Role result = entityManagerDAO.executeQuery(() -> entityManagerDAO.getById(role.getId()));

        // Assert
        assertNotNull(result);
        assertEquals("Tester", result.getName());
    }

    // ____________________________________________________

    @Test
    public void shouldExecuteQueryRunnable() {
        // Arrange
        Role role = new Role();
        role.setName("RunnableRole");
        role.setDescription("Role for runnable test");

        // Act
        entityManagerDAO.executeQuery(() -> entityManagerDAO.create(role));
        Role retrievedRole = entityManagerDAO.getById(role.getId());

        // Assert
        assertNotNull(retrievedRole);
        assertEquals("RunnableRole", retrievedRole.getName());
    }

}