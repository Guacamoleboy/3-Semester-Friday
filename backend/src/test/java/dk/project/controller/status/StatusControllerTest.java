package dk.project.controller.status;

import dk.project.ATest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.hamcrest.CoreMatchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StatusControllerTest extends ATest {

    // Attributes

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should test Status for REST API")
    public void shouldTestStatus() {
        // Arrange
        startServer();
        // Act + Assert
        RestAssured
                .given()
                .when()
                .post("/status")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message.status", equalTo("OK"))
                .body("message.version", equalTo("1.0.0"))
                .body("message.environment", equalTo("test"));
    }


}