package dk.project.controller.auth;

import dk.project.dto.request.ApiRequestDTO;
import dk.project.entity.api.Api;
import dk.project.exception.ApiException;
import dk.project.service.internal.ApiService;
import dk.project.util.ContextHelper;
import dk.project.util.TryCatchHelper;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;

// Why don't I use the CRUDController for this one?
// ________________________________________________
//
// I feel auth / important security features should be implemented in their own individual way
// instead of using the generic Controller class.

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

    public void getApiMeta(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            String keyId = ContextHelper.pathString(ctx, Api.Fields.KEY_ID);
            return apiService.getApiMeta(keyId);
        }, "API meta retrieved");
    }

    // _________________________________________________________________________________________________________________

    public void validateApiKey(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {
            String key = ContextHelper.extractApiKey(ctx);
            ApiRequestDTO apiRequestDTO = ctx.bodyAsClass(ApiRequestDTO.class);
            if (!apiService.validateKey(key, apiRequestDTO.getKeyId())) {
                throw new ApiException(401, "Invalid API key");
            }
        }, "API key validated");
    }

    // _________________________________________________________________________________________________________________

    public void refreshApiKey(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            String key = ContextHelper.extractApiKey(ctx);
            ApiRequestDTO dto = ctx.bodyAsClass(ApiRequestDTO.class);
            String newKey = apiService.refreshKey(dto.getKeyId(), key);
            if (newKey == null) throw new ApiException(401, "Invalid credentials");
            return newKey;
        }, "API key refreshed");
    }

    // _________________________________________________________________________________________________________________

    public void deleteApiKey(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {
            String key = ContextHelper.extractApiKey(ctx);
            ApiRequestDTO dto = ctx.bodyAsClass(ApiRequestDTO.class);
            if (!apiService.deleteKey(dto.getKeyId(), key)) {
                throw new ApiException(401, "Invalid API key or ID");
            }
        }, "API key deleted");
    }

}