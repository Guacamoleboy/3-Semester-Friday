package dk.project.controller.scrape;

import dk.project.ATest;
import dk.project.entity.Medication;
import dk.project.service.internal.MedicationService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import static org.hamcrest.CoreMatchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MedicinScraperControllerTest extends ATest {

    // Attributes
    private MedicationService medicationService;

    // _________________________________________________________________________________________________________________

    @BeforeEach
    public void setupMedicinScraper() {
        startServer("/api");
        this.medicationService = new MedicationService(em);
        beginTransactionIfNeeded();
        Medication medication = Medication.builder()
                .name("Panodil")
                .medicinDkId(670)
                .build();
        medicationService.create(medication);
        commitTransactionIfActive();
        em.clear();
    }

    // _________________________________________________________________________________________________________________

    @Test
    @DisplayName("Should scrape ID 670 correct")
    public void shouldGetSideEffects() {
        // Act + Assert
        RestAssured
                .given()
                .pathParam("id", "670")
                .contentType("application/json")
                .when()
                .post("/scrape/medicin/{id}")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"))
                .body("message", equalTo("Successfully scraped the ID"));
    }

}