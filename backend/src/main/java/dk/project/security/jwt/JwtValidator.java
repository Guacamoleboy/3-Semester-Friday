package dk.project.security.jwt;

import dk.project.dao.impl.UserDAO;
import dk.project.entity.User;
import dk.project.exception.ApiException;
import dk.project.util.ContextHelper;
import io.javalin.http.Context;
import java.util.UUID;

public class JwtValidator {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static boolean isValid(Context ctx) {
        String token = ContextHelper.extractBearerToken(ctx);
        return token != null && JwtUtil.isValid(token);
    }

    // _________________________________________________________________________________________________________________

    public static User getUserFromToken(Context ctx, UserDAO userDAO) {
        String token = ContextHelper.extractBearerToken(ctx);
        if (!JwtUtil.isValid(token)) {
            throw new ApiException(401, "Invalid token");
        }
        UUID userId = JwtService.getUserId(token);
        User user = userDAO.getById(userId);
        ContextHelper.notNull(user, "User");
        return user;
    }

}