package dk.project.route.impl;

import dk.project.controller.impl.QuestionController;
import dk.project.service.internal.QuestionService;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class QuestionRouting {

    private final EntityManager em;
    private final QuestionController questionController;

    // _________________________________________________________________________________________________________________

    public QuestionRouting(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();

        QuestionService questionService = new QuestionService(em);
        this.questionController = new QuestionController(questionService);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {
        return () -> {

            path("/question", () -> {

                // -------------------------------------------------------------------

                get("/all", questionController::getAll);

                // -------------------------------------------------------------------

                get("/{id}", questionController::getById);

                // -------------------------------------------------------------------

                post("/", questionController::create);

                // -------------------------------------------------------------------

                put("/{id}", questionController::updateById);

                // -------------------------------------------------------------------

                delete("/{id}", questionController::deleteById);

            });

        };
    }

}