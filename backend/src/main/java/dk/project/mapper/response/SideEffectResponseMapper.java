package dk.project.mapper.response;

import dk.project.dto.response.SideEffectResponseDTO;
import dk.project.entity.SideEffect;

public class SideEffectResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static SideEffectResponseDTO toDTO(SideEffect sideEffect) {
        SideEffectResponseDTO dto = new SideEffectResponseDTO();
        dto.setId(sideEffect.getId());
        dto.setNote(sideEffect.getNote());
        return dto;
    }

}