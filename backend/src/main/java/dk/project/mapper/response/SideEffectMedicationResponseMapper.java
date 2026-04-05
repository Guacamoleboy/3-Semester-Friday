package dk.project.mapper.response;

import dk.project.dto.response.SideEffectMedicationResponseDTO;
import dk.project.entity.SideEffectMedication;

public class SideEffectMedicationResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static SideEffectMedicationResponseDTO toDTO(SideEffectMedication sideEffectMedication) {
        SideEffectMedicationResponseDTO dto = new SideEffectMedicationResponseDTO();
        dto.setId(sideEffectMedication.getId());
        dto.setName(sideEffectMedication.getName());
        dto.setDescription(sideEffectMedication.getDescription());
        dto.setHeader(sideEffectMedication.getHeader());
        dto.setMedicationCategoryId(sideEffectMedication.getMedicationCategory().getId());
        dto.setMedicationId(sideEffectMedication.getMedication().getId());
        return dto;
    }

}