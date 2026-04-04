package dk.project.controller.auth;

import dk.project.dto.request.LoginRequestDTO;
import dk.project.dto.request.RefreshTokenRequestDTO;
import dk.project.dto.request.UserRequestDTO;
import dk.project.service.internal.auth.AuthService;
import dk.project.util.*;
import dk.project.security.jwt.JwtService;
import jakarta.persistence.EntityManager;
import io.javalin.http.Context;
import java.util.UUID;

public class AuthController {

    // Attributes
    private final AuthService authService;

    // _________________________________________________________________________________________________________________

    public AuthController(EntityManager em) {
        this.authService = new AuthService(em);
    }

    // _________________________________________________________________________________________________________________

    public void login(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            LoginRequestDTO loginRequestDTO = ctx.bodyAsClass(LoginRequestDTO.class);
            return authService.login(loginRequestDTO);
        }, "Login successful");
    }

    // _________________________________________________________________________________________________________________

    public void register(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            UserRequestDTO userRequestDTO = ctx.bodyAsClass(UserRequestDTO.class);
            return authService.register(userRequestDTO);
        }, "User registered successfully");
    }

    // _________________________________________________________________________________________________________________

    public void me(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            String token = ContextHelper.extractBearerToken(ctx);
            UUID userId = JwtService.getUserId(token);
            return authService.me(userId);
        }, "User retrieved");
    }

    // _________________________________________________________________________________________________________________

    public void refresh(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            RefreshTokenRequestDTO request = ctx.bodyAsClass(RefreshTokenRequestDTO.class);
            return authService.refresh(request);
        }, "Token refreshed");
    }

}