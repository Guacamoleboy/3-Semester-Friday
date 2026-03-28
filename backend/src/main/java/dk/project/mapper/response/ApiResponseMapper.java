package dk.project.mapper.response;

import dk.project.dto.response.ApiResponseDTO;
import dk.project.entity.api.Api;

public class ApiResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static ApiResponseDTO toCreateDto(Api api, String unhashedKey) {
        ApiResponseDTO dto = new ApiResponseDTO();
        dto.setName(api.getName());
        dto.setKeyId(api.getKeyId());
        dto.setActive(api.isActive());
        dto.setCreatedAt(api.getCreatedAt());
        dto.setLastUsed(api.getLastUsed());
        dto.setApiKey(unhashedKey);
        return dto;
    }

    // _________________________________________________________________________________________________________________

    public static ApiResponseDTO toDto(Api api) {
        ApiResponseDTO dto = new ApiResponseDTO();
        dto.setName(api.getName());
        dto.setKeyId(api.getKeyId());
        dto.setActive(api.isActive());
        dto.setCreatedAt(api.getCreatedAt());
        dto.setLastUsed(api.getLastUsed());
        return dto;
    }

}