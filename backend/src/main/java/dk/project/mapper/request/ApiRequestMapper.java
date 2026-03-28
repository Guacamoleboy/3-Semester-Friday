package dk.project.mapper.request;

import dk.project.dto.request.ApiRequestDTO;
import dk.project.entity.api.Api;

public class ApiRequestMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static Api toEntity(ApiRequestDTO dto) {
        return Api.builder()
                .name(dto.getName())
                .build();
    }

}