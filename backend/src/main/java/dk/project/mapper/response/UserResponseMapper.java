package dk.project.mapper.response;

import dk.project.dto.response.UserResponseDTO;
import dk.project.entity.User;

public class UserResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static UserResponseDTO toDTO(User user) {
        if (user == null) return null;
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoleId(user.getRole() != null ? user.getRole().getId() : null);
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

}