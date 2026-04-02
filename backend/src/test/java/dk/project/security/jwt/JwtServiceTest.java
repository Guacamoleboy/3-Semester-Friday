package dk.project.security.jwt;

import dk.project.ATest;
import dk.project.entity.Role;
import dk.project.entity.User;
import dk.project.enums.RoleEnum;
import dk.project.service.internal.RoleService;
import dk.project.service.internal.UserService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JwtServiceTest extends ATest {

    // Attributes
    private UserService userService;
    private RoleService roleService;

    // _________________________________________________________________________________________________________________

    @BeforeAll
    public void setupJwt() {
        this.em = emf.createEntityManager();
        this.userService = new UserService(em);
        this.roleService = new RoleService(em);
        beginTransactionIfNeeded();
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (!roleService.existByColumn(roleEnum.getName(), "name")) {
                roleService.create(Role.builder()
                        .name(roleEnum.getName())
                        .description(roleEnum.getDescription())
                        .build());
            }
        }
        if (!userService.existByColumn("bruger1", "username")) {
            User user = User.builder()
                    .username("bruger1")
                    .password("password123!")
                    .email("jonas68@live.dk")
                    .role(roleService.findEntityByColumn(RoleEnum.CLIENT.getName(), "name"))
                    .build();
            userService.createUser(user);
        }
        commitTransactionIfActive();
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should generate Access Token")
    public void shouldGenerateToken() {
        // Arrange
        User user = userService.findEntityByColumn("bruger1", "username");
        // Act
        String token = JwtService.generateAccessToken(user);
        // Assert
        assertNotNull(token);
        assertTrue(JwtUtil.isValid(token));
        assertEquals(user.getId(), JwtService.getUserId(token));
        assertEquals(user.getUsername(), JwtService.getUsername(token));
        assertEquals(user.getRole().getName(), JwtService.getRole(token));
        assertEquals("access", JwtService.getTokenType(token));
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should refresh Access Token")
    public void shouldRefreshToken() {
        // Arrange
        User user = userService.findEntityByColumn("bruger1", "username");
        // Act
        String token = JwtService.generateRefreshToken(user);
        // Assert
        assertNotNull(token, "Refresh token should be generated");
        assertTrue(JwtUtil.isValid(token));
        assertEquals(user.getId(), JwtService.getUserId(token));
        assertEquals("refresh", JwtService.getTokenType(token));
    }

}