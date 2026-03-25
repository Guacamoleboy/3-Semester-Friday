package dk.project.route;

import dk.project.route.impl.AuthRouting;
import dk.project.route.impl.UserRouting;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

public class Routes {

    // Attributes

    // _______________________________________________________________________

    public static EndpointGroup registerRoutes(EntityManagerFactory emf) {

        // Routings
        AuthRouting authRouting = new AuthRouting(emf);
        UserRouting userRouting = new UserRouting(emf);

        // EndpointGroup Return to server
        return () -> {
            authRouting.routes().addEndpoints();
            userRouting.routes().addEndpoints();
        };

    }

}