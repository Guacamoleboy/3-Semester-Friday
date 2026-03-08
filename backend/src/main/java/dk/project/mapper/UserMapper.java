package dk.project.mapper;

import dk.project.dto.UserDTO;
import dk.project.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoleId(user.getRole().getId());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setLastLogin(user.getLastLogin());

        return dto;

    }

}