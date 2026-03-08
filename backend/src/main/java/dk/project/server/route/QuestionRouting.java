package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.path;

public class QuestionRouting {

    // Attributes
    private final EntityManager em;
    // private final QuestionController questionController;

    // _______________________________________________________________________________

    public QuestionRouting(EntityManagerFactory emf){
        em = emf.createEntityManager();
        // questionController = new QuestionController(em);
    }

    // _______________________________________________________________________________

    public EndpointGroup routes(){

        return () -> {
            path("/question", () -> {


            });
        };

    }

    // _______________________________________________________________________________

}