package dk.project.service.internal.auth;

import dk.project.dao.impl.RoleDAO;
import dk.project.dao.impl.UserDAO;
import dk.project.dto.request.LoginRequestDTO;
import dk.project.dto.request.RefreshTokenRequestDTO;
import dk.project.dto.request.UserRequestDTO;
import dk.project.dto.response.AuthResponseDTO;
import dk.project.dto.response.UserResponseDTO;
import dk.project.entity.Role;
import dk.project.entity.User;
import dk.project.enums.RoleEnum;
import dk.project.exception.ApiException;
import dk.project.mapper.request.UserRequestMapper;
import dk.project.mapper.response.UserResponseMapper;
import dk.project.security.jwt.JwtService;
import dk.project.security.jwt.JwtUtil;
import dk.project.util.BCryptHash;
import dk.project.util.ContextHelper;
import jakarta.persistence.EntityManager;
import java.util.UUID;

public class AuthService {

    // Attributes
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    // _________________________________________________________________________________________________________________

    public AuthService(EntityManager em) {
        this.userDAO = new UserDAO(em);
        this.roleDAO = new RoleDAO(em);
    }

    // _________________________________________________________________________________________________________________

    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {

        User user = userDAO.findEntityByColumn(loginRequestDTO.getUsername(), User.Fields.USERNAME);

        if (user == null || !BCryptHash.check(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new ApiException(401, "Invalid credentials");
        }

        String accessToken = JwtService.generateAccessToken(user);
        String refreshToken = JwtService.generateRefreshToken(user);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setRefreshToken(refreshToken);
        authResponseDTO.setUser(UserResponseMapper.toDTO(user));
        return authResponseDTO;
    }

    // _________________________________________________________________________________________________________________

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {

        if (userDAO.existByColumn(userRequestDTO.getUsername(), User.Fields.USERNAME)) {
            throw new ApiException(409, "Username already exists");
        }

        Role role = roleDAO.findEntityByColumn(
                RoleEnum.valueOf(userRequestDTO.getRole().toUpperCase()).getName(),
                Role.Fields.NAME);

        ContextHelper.notNull(role, "Role");

        User user = UserRequestMapper.toEntity(userRequestDTO, role);
        user.setPassword(BCryptHash.hash(user.getPassword()));
        user.setEmail(BCryptHash.hash(user.getEmail()));
        userDAO.create(user);

        return UserResponseMapper.toDTO(user);
    }

    // _________________________________________________________________________________________________________________

    public UserResponseDTO me(UUID userId) {
        User user = userDAO.getById(userId);
        ContextHelper.notNull(user, "User");
        return UserResponseMapper.toDTO(user);
    }

    // _________________________________________________________________________________________________________________

    public AuthResponseDTO refresh(RefreshTokenRequestDTO request) {
        String refreshToken = request.getRefreshToken();
        if (!JwtUtil.isValid(refreshToken)) {
            throw new ApiException(401, "Invalid refresh token");
        }
        UUID userId = JwtService.getUserId(refreshToken);
        User user = userDAO.getById(userId);
        String newAccessToken = JwtService.generateAccessToken(user);
        AuthResponseDTO response = new AuthResponseDTO();
        response.setAccessToken(newAccessToken);
        return response;
    }

}