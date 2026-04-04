package dk.project.controller.data;

import dk.project.ATest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.hamcrest.CoreMatchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PopulateControllerTest extends ATest {

    // Attributes

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should Populate DB")
    public void shouldPopulateDatabase() {
        // Arrange
        startServer();
        // Act + Assert
        RestAssured
                .given()
                .when()
                .post("/data/populate")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Database populated"));
    }

}