package dk.project.route.impl;

import dk.project.controller.impl.BaselineIndividualController;
import dk.project.enums.AccessLevelEnum;
import dk.project.security.access.AccessValidator;
import dk.project.service.internal.BaselineIndividualService;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class BaselineIndividualSideEffectRouting {

    // Attributes
    private final BaselineIndividualController controller;

    // _________________________________________________________________________________________________________________

    public BaselineIndividualSideEffectRouting(EntityManagerFactory emf) {
        controller = new BaselineIndividualController(new BaselineIndividualService(emf.createEntityManager()));
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {

            path("/baseline/individual/sideeffect", () -> {

                // -------------------------------------------------------------------

                get("/all", AccessValidator.access(
                        controller::getSideEffects,
                        AccessLevelEnum.ADMIN,
                        AccessLevelEnum.API
                ));

                // -------------------------------------------------------------------

                post("/", AccessValidator.access(
                        controller::addSideEffect,
                        AccessLevelEnum.JWT_OR_API,
                        AccessLevelEnum.ADMIN
                ));

                // -------------------------------------------------------------------

                delete("/{id}", AccessValidator.access(
                        controller::removeSideEffect,
                        AccessLevelEnum.SUPPORT,
                        AccessLevelEnum.ADMIN
                ));

            });

        };
    }

}