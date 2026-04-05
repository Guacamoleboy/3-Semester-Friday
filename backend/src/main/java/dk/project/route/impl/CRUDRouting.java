package dk.project.route.impl;

import dk.project.controller.impl.CRUDController;
import dk.project.enums.AccessLevelEnum;
import dk.project.route.IRoute;
import dk.project.security.access.AccessValidator;
import io.javalin.apibuilder.EndpointGroup;
import static io.javalin.apibuilder.ApiBuilder.*;

public abstract class CRUDRouting<T> implements IRoute {

    // Attributes
    protected final String basePath;
    protected final CRUDController<T> controller;

    // _________________________________________________________________________________________________________________

    public CRUDRouting(String basePath, CRUDController<T> controller) {
        this.basePath = basePath;
        this.controller = controller;
    }

    // _________________________________________________________________________________________________________________

    @Override
    public EndpointGroup routes() {
        return () -> {

            path(basePath, () -> {

                // -------------------------------------------------------------------

                get("/all", AccessValidator.access(
                        this.controller::getAll,
                        AccessLevelEnum.ADMIN,
                        AccessLevelEnum.SUPPORT
                ));

                // -------------------------------------------------------------------

                get("/{id}", AccessValidator.access(
                        this.controller::getById,
                        AccessLevelEnum.JWT_OR_API,
                        AccessLevelEnum.ADMIN,
                        AccessLevelEnum.SUPPORT
                ));

                // -------------------------------------------------------------------

                post("/", AccessValidator.access(
                        this.controller::create,
                        AccessLevelEnum.JWT_OR_API,
                        AccessLevelEnum.ADMIN
                ));

                // -------------------------------------------------------------------

                put("/{id}", AccessValidator.access(
                        this.controller::updateById,
                        AccessLevelEnum.JWT_AND_API,
                        AccessLevelEnum.ADMIN,
                        AccessLevelEnum.SUPPORT
                ));

                // -------------------------------------------------------------------

                delete("/{id}", AccessValidator.access(
                        this.controller::deleteById,
                        AccessLevelEnum.ADMIN,
                        AccessLevelEnum.SUPPORT,
                        AccessLevelEnum.JWT_AND_API
                ));

                // -------------------------------------------------------------------

                delete("/all", AccessValidator.access(
                        this.controller::deleteAll,
                        AccessLevelEnum.ADMIN
                ));

                // -------------------------------------------------------------------

                delete("/all/safe", AccessValidator.access(
                        this.controller::deleteAllSafe,
                        AccessLevelEnum.ADMIN
                ));

            });

        };

    }

}