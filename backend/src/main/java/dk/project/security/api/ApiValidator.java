package dk.project.security.api;

import dk.project.util.ContextHelper;
import io.javalin.http.Context;

public class ApiValidator {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static boolean isValid(Context ctx) {
        String apiKey = ContextHelper.extractApiKey(ctx);
        return apiKey != null;
    }

}