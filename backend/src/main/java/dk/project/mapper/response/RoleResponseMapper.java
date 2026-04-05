package dk.project.mapper.response;

import dk.project.dto.response.RoleResponseDTO;
import dk.project.entity.Role;

public class RoleResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static RoleResponseDTO toDTO(Role role) {
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        return dto;
    }

}