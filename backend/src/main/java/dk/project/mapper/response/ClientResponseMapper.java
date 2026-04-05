package dk.project.mapper.response;

import dk.project.dto.response.ClientResponseDTO;
import dk.project.entity.Client;

public class ClientResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static ClientResponseDTO toDTO(Client client) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId());
        dto.setIdEnding(client.getIdEnding());
        dto.setCreatedAt(client.getCreatedAt());
        dto.setLastLogin(client.getLastLogin());
        return dto;
    }

}