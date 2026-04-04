package dk.project.server;

import dk.project.config.DotEnv;
import dk.project.config.DotEnvLog;
import dk.project.config.HibernateConfig;
import dk.project.exception.ApiException;
import dk.project.exception.DatabaseException;
import dk.project.exception.ResourceNotFoundException;
import dk.project.route.Routes;
import dk.project.security.access.AccessValidator;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import jakarta.persistence.EntityManagerFactory;
import java.util.Map;

// _____________________________________________________________________________________________________________________

// _____________________________________________________________________________________________________________________

// Important links
// _______________

// 1) - https://javalin.io/documentation#handlers
// 2) - https://javalin.io/news/javalin-7.0.0-stable.html
// 3) - https://www.javadoc.io/doc/io.javalin/javalin/7.0.0-alpha.3/io/javalin/Javalin.html

// _____________________________________________________________________________________________________________________

// _____________________________________________________________________________________________________________________

public class Server {

    // Attributes
    private Javalin app;
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private final Integer port = DotEnv.getServerPort();

    // _________________________________________________________________________________________________________________

    public void start() {

        // Singleton check
        if (app != null){
            return;
        }

        // Logging
        DotEnvLog.logEnvInfo();

        // Javalin setup
        app = Javalin.create(config -> {
            configureRouting(config);
            configurePlugins(config);
            configureExceptionHandling(config);
        }).start(port);

    }

    // _________________________________________________________________________________________________________________

    private void configureRouting(JavalinConfig config) {
        config.router.contextPath = DotEnv.getApiBasePath();
        config.routes.apiBuilder(Routes.registerRoutes(emf));
    }

    // _________________________________________________________________________________________________________________

    private void configurePlugins(JavalinConfig config) {
        config.bundledPlugins.enableRouteOverview(DotEnv.getRouteOverviewPath());
    }

    // _________________________________________________________________________________________________________________

    private void configureExceptionHandling(JavalinConfig config) {

        // ValidationException
        config.routes.exception(ValidationException.class, (e, ctx) -> {
            ctx.status(400).json(Map.of(
                    "status", "error",
                    "code", 400,
                    "message", e.getErrors().toString()
            ));
        });

        // ApiException
        config.routes.exception(ApiException.class, (e, ctx) -> {
            ctx.status(e.getCode())
                .json(Map.of(
                        "status", "error",
                        "code", e.getCode(),
                        "message", e.getMessage()
                ));
        });

        // ResourceNotFoundException
        config.routes.exception(ResourceNotFoundException.class, (e, ctx) -> {
            ctx.status(HttpStatus.NOT_FOUND)
                .json(Map.of(
                        "status", "error",
                        "code", 404,
                        "message", e.getMessage()
                ));
        });

        // DatabaseException
        config.routes.exception(DatabaseException.class, (e, ctx) -> {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .json(Map.of(
                        "status", "error",
                        "code", 500,
                        "message", "Database error"
                ));
        });

        // Default Exception Handle
        config.routes.exception(Exception.class, (e, ctx) -> {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .json(Map.of(
                        "status", "error",
                        "code", 500,
                        "message", "Internal Server Error"
                ));
        });

    }

    // _________________________________________________________________________________________________________________

    public void stop() {
        if (app != null) {
            app.stop();
            app = null;
        }
    }

    // _________________________________________________________________________________________________________________

    public Javalin getApp() {
        return app;
    }

}