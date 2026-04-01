package dk.project.controller.auth;

import dk.project.ATest;
import dk.project.dto.request.LoginRequestDTO;
import dk.project.dto.request.RefreshTokenRequestDTO;
import dk.project.dto.request.UserRequestDTO;
import dk.project.entity.Role;
import dk.project.entity.User;
import dk.project.enums.RoleEnum;
import dk.project.service.internal.RoleService;
import dk.project.service.internal.UserService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest extends ATest {

    // Attributes
    private UserService userService;
    private RoleService roleService;
    private String accessToken;
    private String refreshToken;

    // _________________________________________________________________________________________________________________

    @BeforeEach
    public void setupAuth() {
        // Setup
        startServer("/api");
        this.userService = new UserService(em);
        this.roleService = new RoleService(em);

        beginTransactionIfNeeded();

        // Role(s)
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (!roleService.existByColumn(roleEnum.getName(), "name")) {
                roleService.create(Role.builder()
                        .name(roleEnum.getName())
                        .description(roleEnum.getDescription())
                        .build());
            }
        }

        // User
        if (!userService.existByColumn("testUser", "username")) {
            User user = User.builder()
                    .username("testUser")
                    .password("password123!")
                    .email("jonas68@live.dk")
                    .role(roleService.findEntityByColumn(RoleEnum.CLIENT.getName(), "name"))
                    .build();
            userService.createUser(user);
        }

        commitTransactionIfActive();

        loginAndSave();

    }

    // _________________________________________________________________________________________________________________

    private void loginAndSave() {
        var loginRequest = new LoginRequestDTO();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("password123!");
        var response = RestAssured
                        .given()
                        .contentType("application/json")
                        .body(loginRequest)
                        .when()
                        .post("/auth/login")
                        .then()
                        .statusCode(200)
                        .extract()
                        .body()
                        .jsonPath();
        this.accessToken = response.getString("data.access_token");
        this.refreshToken = response.getString("data.refresh_token");
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should log in")
    public void shouldLogin() {
        // Arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername("testUser");
        loginRequestDTO.setPassword("password123!");
        // Act + Assert
        RestAssured
                .given()
                .contentType("application/json")
                .body(loginRequestDTO)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Login successful"))
                .body("data.access_token", notNullValue())
                .body("data.refresh_token", notNullValue());
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should fail login")
    public void shouldFailLogin() {
        // Arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUsername("invalidUser");
        loginRequestDTO.setPassword("passwordFail");
        // Act + Assert
        RestAssured.given()
                .contentType("application/json")
                .body(loginRequestDTO)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401)
                .body("status", equalTo("error"))
                .body("message", equalTo("Invalid credentials"));
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should register new user")
    public void shouldRegister() {
        // Arrange
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("newUser");
        userRequestDTO.setPassword("password123!");
        userRequestDTO.setEmail("jonas69@live.dk");
        userRequestDTO.setRole("CLIENT");
        // Act + Assert
        RestAssured.given()
                .contentType("application/json")
                .body(userRequestDTO)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("User registered successfully"));
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should get user info")
    public void shouldGetMe() {
        RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/auth/me")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("data.username", equalTo("testUser"));
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should refresh token")
    public void shouldRefreshToken() {
        RefreshTokenRequestDTO requestDTO = new RefreshTokenRequestDTO();
        requestDTO.setRefreshToken(refreshToken);

        RestAssured
                .given()
                .contentType("application/json")
                .body(requestDTO)
                .when()
                .post("/auth/refresh")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("data.access_token", notNullValue());
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should log out")
    public void shouldLogout() {
        RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .post("/auth/logout")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Logout successful"));
    }

}