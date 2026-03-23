package dk.project.controller;

import dk.project.dao.RoleDAO;
import dk.project.dao.UserDAO;
import dk.project.dto.UserDTO;
import dk.project.dto.request.LoginRequestDTO;
import dk.project.dto.request.RefreshTokenRequestDTO;
import dk.project.dto.request.RegisterRequestDTO;
import dk.project.dto.response.AuthResponseDTO;
import dk.project.entity.Role;
import dk.project.entity.User;
import dk.project.enums.RoleEnum;
import dk.project.exception.ApiException;
import dk.project.mapper.UserMapper;
import dk.project.util.*;
import dk.project.util.jwt.JwtUser;
import dk.project.util.jwt.JwtUtil;
import dk.project.util.auth.AuthUtil;
import io.javalin.http.HttpResponseException;
import jakarta.persistence.EntityManager;
import io.javalin.http.Context;
import java.util.UUID;

public class AuthController {

    // Attributes
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    // _________________________________________________________________________________

    public AuthController(EntityManager em) {
        userDAO = new UserDAO(em);
        roleDAO = new RoleDAO(em);
    }

    // _________________________________________________________________________________

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
            response.setUser(UserMapper.toDTO(user));
            return response;
        }, "Login successful");
    }

    // _________________________________________________________________________________

    public void register(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () -> {
            RegisterRequestDTO request = ctx.bodyAsClass(RegisterRequestDTO.class);

            if (userDAO.existByColumn(request.getUsername(), "username")) {
                throw new ApiException(409, "Username already exists");
            }

            Role role = roleDAO.findEntityByColumn(
                    RoleEnum.valueOf(request.getRole().toUpperCase()).getName(),
                    "name"
            );

            ContextHelper.checkNotNull(role, "Role");
            String hashedPassword = BCryptHash.hash(request.getPassword());
            String hashedEmail = BCryptHash.hash(request.getEmail());

            User user = User.builder()
                    .username(request.getUsername())
                    .email(hashedEmail)
                    .password(hashedPassword)
                    .role(role)
                    .build();
            userDAO.create(user);

            UserDTO userDTO = UserMapper.toDTO(user);
            return userDTO;
        }, "User registered successfully");
    }

    // _________________________________________________________________________________
    // JWT Logout

    public void logout(Context ctx) {
        TryCatchHelper.tryCatchHelperVoid(ctx, () -> {
        }, "Logout successful");
    }

    // _________________________________________________________________________________
    // Personal data

    public void me(Context ctx) {
        TryCatchHelper.tryCatchHelper(ctx, () ->
            UserMapper.toDTO(AuthUtil.getUserFromToken(ctx, userDAO)), "User retrieved"
        );
    }

    // _________________________________________________________________________________
    // JWT Refresh

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

            return java.util.Map.of("access_token", newAccessToken);
        }, "Token refreshed");
    }

}