package dk.project.mapper.request;

import dk.project.dto.request.UserRequestDTO;
import dk.project.entity.User;
import dk.project.entity.Role;

public class UserRequestMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static User toEntity(UserRequestDTO dto, Role role) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(role)
                .build();
    }

}