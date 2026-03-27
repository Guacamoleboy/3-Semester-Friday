package dk.project.controller.auth;

import dk.project.dto.request.ApiRequestDTO;
import dk.project.exception.ApiException;
import dk.project.service.internal.ApiService;
import dk.project.util.ContextHelper;
import dk.project.util.TryCatchHelper;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

public class ApiController {

    // Attributes
    private final ApiService apiService;

    // _________________________________________________________________________________________________________________

    public ApiController(EntityManager em) {
        this.apiService = new ApiService(em);
    }

    // _________________________________________________________________________________________________________________

    public void createApiKey(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            ApiRequestDTO dto = ctx.bodyAsClass(ApiRequestDTO.class);
            return apiService.createApiKey(dto.getName());
        }, "Api key generated");
    }

    // _________________________________________________________________________________________________________________

    public void validateApiKey(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            String key = ContextHelper.extractApiKey(ctx);
            if (!apiService.validateKey(key)) {
                throw new ApiException(401, "Invalid API key");
            }
            return true;
        }, "API key validated");
    }

    // _________________________________________________________________________________________________________________

    public void refreshApiKey(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            String key = ContextHelper.extractApiKey(ctx);
            String newKey = apiService.refreshKey(key);
            if (newKey == null) {
                throw new ApiException(404, "API key not found or inactive");
            }
            return newKey;
        }, "API key refreshed");
    }

    // _________________________________________________________________________________________________________________

    public void deleteApiKey(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {
            String key = ContextHelper.extractApiKey(ctx);
            if (!apiService.deleteKey(key)) {
                throw new ApiException(404, "API key not found");
            }
        }, "API key deleted");
    }

}