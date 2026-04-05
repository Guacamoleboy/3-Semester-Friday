package dk.project.mapper.response;

import dk.project.dto.response.MedicationResponseDTO;
import dk.project.entity.Medication;

public class MedicationResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static MedicationResponseDTO toDTO(Medication medication) {
        MedicationResponseDTO dto = new MedicationResponseDTO();
        dto.setId(medication.getId());
        dto.setName(medication.getName());
        dto.setDescription(medication.getDescription());
        dto.setMedicinDkId(medication.getMedicinDkId());
        return dto;
    }

}