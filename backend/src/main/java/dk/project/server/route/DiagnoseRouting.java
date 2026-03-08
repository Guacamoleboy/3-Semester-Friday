package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class DiagnoseRouting {

    // Attributes
    private final EntityManager em;
    // private final DiagnoseController diagnoseController;
    // private final DiagnoseClientController diagnoseClientController;
    // private final DiagnoseTypeController diagnoseTypeController;

    // ___________________________________________________________________________

    public DiagnoseRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        // diagnoseController = new DiagnoseController(em);
        // diagnoseClientController = new DiagnoseClientController(em);
        // diagnoseTypeController = new DiagnoseTypeController(em);
    }

    // ___________________________________________________________________________

    public EndpointGroup routes(){
        return () -> {
            path("/diagnose", () -> {
                // get("/all", diagnoseController::getAll);
                // get("/{id}", diagnoseController::getById);
                // post("/", diagnoseController::create);
                // put("/{id}", diagnoseController::updateById);
                // delete("/{id}", diagnoseController::deleteById);

                // Diagnose Type
                path("/type", () -> {
                    // get("/all", diagnoseTypeController::getAll);
                    // get("/{id}", diagnoseTypeController::getById);
                    // post("/", diagnoseTypeController::create);
                    // put("/{id}", diagnoseTypeController::updateById);
                    // delete("/{id}", diagnoseTypeController::deleteById);
                });

                // Client
                path("/client", () -> {
                    // get("/all", diagnoseClientController::getAll);
                    // get("/{id}", diagnoseClientController::getById);
                    // post("/", diagnoseClientController::create);
                    // put("/{id}", diagnoseClientController::updateById);
                    // delete("/{id}", diagnoseClientController::deleteById);
                });

            });
        };
    }

}