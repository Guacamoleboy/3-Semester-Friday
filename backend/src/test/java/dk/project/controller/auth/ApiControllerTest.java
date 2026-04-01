package dk.project.controller.auth;

import dk.project.ATest;
import dk.project.dto.request.ApiRequestDTO;
import dk.project.dto.request.LoginRequestDTO;
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
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiControllerTest extends ATest {

    // Attributes
    private UserService userService;
    private RoleService roleService;
    private String apiKey;
    private String keyId;
    private String accessToken;
    private String refreshToken;

    // _________________________________________________________________________________________________________________

    @BeforeEach
    public void setupApi() {
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
    @DisplayName("Should create API key")
    public void shouldCreateApiKey() {
        var apiRequestDTO = new ApiRequestDTO();
        apiRequestDTO.setName("Test API");
        var response = RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .contentType("application/json")
                .body(apiRequestDTO)
                .when()
                .post("/access/create")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();
        apiKey = response.getString("data.api_key");
        keyId = response.getString("data.key_id");
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should get API meta")
    public void shouldGetApiMeta() {
        shouldCreateApiKey();
        var response = RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get("/access/" + keyId)
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .extract()
                .body()
                .jsonPath();
        assertEquals(apiKey, response.getString("data.api_key"));
        assertEquals(keyId, response.getString("data.key_id"));
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should validate API key")
    public void shouldValidateApiKey() {
        shouldCreateApiKey();
        var apiRequestDTO = new ApiRequestDTO();
        apiRequestDTO.setKeyId(keyId);
        RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .header("X-API-Key", apiKey)
                .contentType("application/json")
                .body(apiRequestDTO)
                .when()
                .post("/access/validate")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"));
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should refresh API key")
    public void shouldRefreshApiKey() {
        shouldCreateApiKey();
        var apiRequestDTO = new ApiRequestDTO();
        apiRequestDTO.setKeyId(keyId);
        var response = RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .header("X-API-Key", apiKey)
                .contentType("application/json")
                .body(apiRequestDTO)
                .when()
                .post("/access/refresh")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .extract()
                .body()
                .jsonPath();
        String newKey = response.getString("data");
        assertNotNull(newKey);
        assertNotEquals(apiKey, newKey);
        apiKey = newKey;
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should delete API key")
    public void shouldDeleteApiKey() {
        shouldCreateApiKey();
        var apiRequestDTO = new ApiRequestDTO();
        apiRequestDTO.setKeyId(keyId);
        RestAssured
                .given()
                .header("Authorization", "Bearer " + accessToken)
                .header("X-API-Key", apiKey)
                .contentType("application/json")
                .body(apiRequestDTO)
                .when()
                .delete("/access/delete")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"));
    }

}