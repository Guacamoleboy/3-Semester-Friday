package dk.project.route.impl;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class QuestionRouting {

    private final EntityManager em;
    // private final QuestionController questionController;

    // ______________________________________________________

    public QuestionRouting(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
        // this.questionController = new QuestionController(em);
    }

    // ______________________________________________________

    public EndpointGroup routes() {
        return () -> {
            path("/question", () -> {

                // get("/all", questionController::getAll);
                // get("/{id}", questionController::getById);
                // post("/", questionController::create);
                // put("/{id}", questionController::updateById);
                // delete("/{id}", questionController::deleteById);

                // Param Search. For Example:
                // api.moodmap.dk/question?medication=2&diagnose=5

                // get("/", questionController::paramSearch);

            });
        };
    }

}