package dk.project.security.api;

import dk.project.exception.ApiException;
import dk.project.util.ContextHelper;
import dk.project.security.jwt.JwtUtil;
import io.javalin.http.Context;
import java.util.List;

public class AccessValidator {

    // Attributes
    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/refresh",
            "/api/auth/logout",
            "/api/data/populate",
            "/api/scrape/medicin",
            "/api/health",
            "/api/status"
    );

    // _________________________________________________________________________________________________________________
    // handle() is a javalin method that runs before controllers

    public static void handle(Context ctx) {
        if (!(isPublic(ctx) || hasJwt(ctx))) {
            throw new ApiException(401, "Unauthorized");
        }
    }

    // _________________________________________________________________________________________________________________

    private static boolean isPublic(Context ctx) {
        return PUBLIC_PATHS.stream().anyMatch(path -> ctx.path().startsWith(path));
    }

    // _________________________________________________________________________________________________________________

    private static boolean hasJwt(Context ctx) {
        String token = ContextHelper.extractBearerToken(ctx);
        if (token == null) {
            return false;
        }
        return JwtUtil.isValid(token);
    }

}