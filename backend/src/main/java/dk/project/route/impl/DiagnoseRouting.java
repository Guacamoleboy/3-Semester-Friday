package dk.project.route.impl;

import dk.project.controller.impl.DiagnoseClientController;
import dk.project.controller.impl.DiagnoseController;
import dk.project.controller.impl.DiagnoseTypeController;
import dk.project.service.internal.DiagnoseClientService;
import dk.project.service.internal.DiagnoseService;
import dk.project.service.internal.DiagnoseTypeService;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class DiagnoseRouting {

    // Attributes
    private final EntityManager em;
    private final DiagnoseController diagnoseController;
    private final DiagnoseClientController diagnoseClientController;
    private final DiagnoseTypeController diagnoseTypeController;

    // _________________________________________________________________________________________________________________

    public DiagnoseRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();

        DiagnoseService diagnoseService = new DiagnoseService(em);
        diagnoseController = new DiagnoseController(diagnoseService);

        DiagnoseClientService diagnoseClientService = new DiagnoseClientService(em);
        diagnoseClientController = new DiagnoseClientController(diagnoseClientService);

        DiagnoseTypeService diagnoseTypeService = new DiagnoseTypeService(em);
        diagnoseTypeController = new DiagnoseTypeController(diagnoseTypeService);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes(){
        return () -> {
            path("/diagnose", () -> {

                // -------------------------------------------------------------------

                get("/all", diagnoseController::getAll);

                // -------------------------------------------------------------------

                get("/{id}", diagnoseController::getById);

                // -------------------------------------------------------------------

                post("/", diagnoseController::create);

                // -------------------------------------------------------------------

                put("/{id}", diagnoseController::updateById);

                // -------------------------------------------------------------------

                delete("/{id}", diagnoseController::deleteById);

                // -------------------------------------------------------------------

                path("/type", () -> {

                    // -------------------------------------------------------------------

                    get("/all", diagnoseTypeController::getAll);

                    // -------------------------------------------------------------------

                    get("/{id}", diagnoseTypeController::getById);

                    // -------------------------------------------------------------------

                    post("/", diagnoseTypeController::create);

                    // -------------------------------------------------------------------

                    put("/{id}", diagnoseTypeController::updateById);

                    // -------------------------------------------------------------------

                    delete("/{id}", diagnoseTypeController::deleteById);

                });

                // -------------------------------------------------------------------

                path("/client", () -> {

                    // -------------------------------------------------------------------

                    get("/all", diagnoseClientController::getAll);

                    // -------------------------------------------------------------------

                    get("/{id}", diagnoseClientController::getById);

                    // -------------------------------------------------------------------

                    post("/", diagnoseClientController::create);

                    // -------------------------------------------------------------------

                    put("/{id}", diagnoseClientController::updateById);

                    // -------------------------------------------------------------------

                    delete("/{id}", diagnoseClientController::deleteById);

                });

            });
        };
    }

}