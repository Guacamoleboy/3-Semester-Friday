package dk.project.security.access;

import dk.project.enums.AccessLevelEnum;
import dk.project.enums.RoleEnum;
import dk.project.exception.ApiException;
import dk.project.security.api.ApiValidator;
import dk.project.security.jwt.JwtService;
import dk.project.security.jwt.JwtUtil;
import dk.project.security.jwt.JwtValidator;
import dk.project.util.ContextHelper;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AccessValidator {

    // Attributes

    // _________________________________________________________________________________________________________________
    // Used on Routes that share the same access level for all sub-routes.

    public static void handle(Context ctx, AccessLevelEnum accessLevelEnum) {

        switch (accessLevelEnum) {
            case PUBLIC:
                return;
            case JWT:
                handleJwt(ctx);
                break;
            case API:
                handleApi(ctx);
                break;
            case JWT_OR_API:
                handleJwtOrApi(ctx);
                break;
            case JWT_AND_API:
                handleJwtAndApi(ctx);
                break;
            case ADMIN:
                handleStaff(ctx, RoleEnum.ADMIN);
                break;
            case MODERATOR:
                handleStaff(ctx, RoleEnum.MODERATOR);
                break;
            case SUPPORT:
                handleStaff(ctx, RoleEnum.SUPPORT);
                break;
        }

    }

    // _________________________________________________________________________________________________________________

    public static Handler access(Handler handler, AccessLevelEnum... accessLevelEnums) {
        return ctx -> {
            boolean authorized = false;
            for (AccessLevelEnum level : accessLevelEnums) {
                try {
                    handle(ctx, level);
                    authorized = true;
                    break;
                } catch (ApiException e) {
                    e.getStackTrace();
                }
            }
            if (!authorized) {
                throw new ApiException(403, "Access denied");
            }
            handler.handle(ctx);
        };
    }

    // _________________________________________________________________________________________________________________

    private static void handleJwt(Context ctx) {
        if (!JwtValidator.isValid(ctx)) {
            throw new ApiException(401, "JWT required");
        }
    }

    // _________________________________________________________________________________________________________________

    private static void handleApi(Context ctx) {
        if (!ApiValidator.isValid(ctx)) {
            throw new ApiException(401, "API key required");
        }
    }

    // _________________________________________________________________________________________________________________

    private static void handleJwtOrApi(Context ctx) {
        if (!(JwtValidator.isValid(ctx) || ApiValidator.isValid(ctx))) {
            throw new ApiException(401, "JWT or API key required");
        }
    }

    // _________________________________________________________________________________________________________________

    private static void handleJwtAndApi(Context ctx) {
        if (!(JwtValidator.isValid(ctx) && ApiValidator.isValid(ctx))) {
            throw new ApiException(401, "JWT and API key required");
        }
    }

    // _________________________________________________________________________________________________________________

    private static void handleStaff(Context ctx, RoleEnum roleEnum) {
        String token = ContextHelper.extractBearerToken(ctx);
        if (token == null || !JwtUtil.isValid(token)) {
            throw new ApiException(401, "JWT required");
        }
        String extractedRole = JwtService.getRole(token);
        if (!roleEnum.getName().equals(extractedRole)) {
            throw new ApiException(403, roleEnum.getName() + " access required");
        }
    }

}