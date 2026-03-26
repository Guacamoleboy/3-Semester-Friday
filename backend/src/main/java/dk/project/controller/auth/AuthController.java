package dk.project.controller.auth;

import dk.project.dao.impl.RoleDAO;
import dk.project.dao.impl.UserDAO;
import dk.project.dto.request.LoginRequestDTO;
import dk.project.dto.request.RefreshTokenRequestDTO;
import dk.project.dto.request.UserRequestDTO;
import dk.project.dto.response.AuthResponseDTO;
import dk.project.entity.Role;
import dk.project.entity.User;
import dk.project.enums.RoleEnum;
import dk.project.exception.ApiException;
import dk.project.mapper.request.UserRequestMapper;
import dk.project.mapper.response.UserResponseMapper;
import dk.project.util.*;
import dk.project.security.jwt.JwtUser;
import dk.project.security.jwt.JwtUtil;
import dk.project.security.auth.AuthUtil;
import jakarta.persistence.EntityManager;
import io.javalin.http.Context;
import java.util.Map;
import java.util.UUID;

public class AuthController {

    // Attributes
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    // _________________________________________________________________________________________________________________

    public AuthController(EntityManager em) {
        userDAO = new UserDAO(em);
        roleDAO = new RoleDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public void login(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            LoginRequestDTO request = ctx.bodyAsClass(LoginRequestDTO.class);

            User user = userDAO.findEntityByColumn(request.getUsername(), "username");
            if (user == null || !BCryptHash.check(request.getPassword(), user.getPassword())) {
                throw new ApiException(401, "Invalid credentials");
            }

            String accessToken = JwtUser.generateAccessToken(user);
            String refreshToken = JwtUser.generateRefreshToken(user);

            AuthResponseDTO response = new AuthResponseDTO();
            response.setAccessToken(accessToken);
            response.setRefreshToken(refreshToken);
            response.setUser(UserResponseMapper.toDTO(user));
            return response;
        }, "Login successful");
    }

    // _________________________________________________________________________________________________________________

    public void register(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            UserRequestDTO request = ctx.bodyAsClass(UserRequestDTO.class);

            if (userDAO.existByColumn(request.getUsername(), "username")) {
                throw new ApiException(409, "Username already exists");
            }

            Role role = roleDAO.findEntityByColumn(
                    RoleEnum.valueOf(request.getRole().toUpperCase()).getName(),
                    "name"
            );
            ContextHelper.notNull(role, "Role");

            User user = UserRequestMapper.toEntity(request, role);
            user.setPassword(BCryptHash.hash(user.getPassword()));
            user.setEmail(BCryptHash.hash(user.getEmail()));

            userDAO.create(user);

            return UserResponseMapper.toDTO(user);
        }, "User registered successfully");
    }

    // _________________________________________________________________________________________________________________

    public void logout(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {
        }, "Logout successful");
    }

    // _________________________________________________________________________________________________________________

    public void me(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            User user = AuthUtil.getUserFromToken(ctx, userDAO);
            return UserResponseMapper.toDTO(user);
        }, "User retrieved");
    }

    // _________________________________________________________________________________________________________________

    public void refresh(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {

            RefreshTokenRequestDTO request = ctx.bodyAsClass(RefreshTokenRequestDTO.class);
            String refreshToken = request.getRefreshToken();

            if (!JwtUtil.isValid(refreshToken)) {
                throw new ApiException(401, "Invalid refresh token");
            }

            UUID userId = JwtUser.getUserId(refreshToken);
            User user = userDAO.getById(userId);
            String newAccessToken = JwtUser.generateAccessToken(user);

            return Map.of("access_token", newAccessToken);
        }, "Token refreshed");
    }

}