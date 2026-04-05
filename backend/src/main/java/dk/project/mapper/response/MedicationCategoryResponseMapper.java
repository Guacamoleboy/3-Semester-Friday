package dk.project.mapper.response;

import dk.project.dto.response.MedicationCategoryResponseDTO;
import dk.project.entity.MedicationCategory;

public class MedicationCategoryResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static MedicationCategoryResponseDTO toDTO(MedicationCategory medicationCategory) {
        MedicationCategoryResponseDTO dto = new MedicationCategoryResponseDTO();
        dto.setId(medicationCategory.getId());
        dto.setName(medicationCategory.getName());
        dto.setDescription(medicationCategory.getDescription());
        dto.setWarningLevel(medicationCategory.getWarningLevel());
        return dto;
    }

}