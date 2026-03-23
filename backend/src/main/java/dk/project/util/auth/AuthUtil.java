package dk.project.util.auth;

import dk.project.dao.impl.UserDAO;
import dk.project.entity.User;
import dk.project.util.ContextHelper;
import dk.project.util.jwt.JwtUser;
import dk.project.util.jwt.JwtUtil;
import io.javalin.http.Context;
import io.javalin.http.HttpResponseException;
import java.util.UUID;

public class AuthUtil {

    public static User getUserFromToken(Context ctx, UserDAO userDAO) {
        // Fetch
        String token = ContextHelper.extractBearerToken(ctx);

        // Validation
        if (!JwtUtil.isValid(token)) {
            throw new HttpResponseException(401, "Invalid token");
        }

        // Act & Check
        UUID userId = JwtUser.getUserId(token);
        User user = userDAO.getById(userId);
        ContextHelper.notNull(user, "User");

        return user;
    }

}