package dk.project.route.impl;

import dk.project.controller.impl.QuestionController;
import dk.project.entity.Question;
import dk.project.service.internal.QuestionService;
import jakarta.persistence.EntityManagerFactory;

public class QuestionRouting extends CRUDRouting<Question> {

    // _________________________________________________________________________________________________________________

    public QuestionRouting(EntityManagerFactory emf) {
        super("/question", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static QuestionController createController(EntityManagerFactory emf) {
        return new QuestionController(new QuestionService(emf.createEntityManager()));
    }

}