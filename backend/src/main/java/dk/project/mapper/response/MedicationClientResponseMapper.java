package dk.project.mapper.response;

import dk.project.dto.response.MedicationClientResponseDTO;
import dk.project.entity.MedicationClient;

public class MedicationClientResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static MedicationClientResponseDTO toDTO(MedicationClient medicationClient) {
        MedicationClientResponseDTO dto = new MedicationClientResponseDTO();
        dto.setId(medicationClient.getId());
        dto.setMedicationId(medicationClient.getMedication().getId());
        dto.setClientId(medicationClient.getClient().getId());
        dto.setAmount(medicationClient.getAmount());
        dto.setTimeline(medicationClient.getTimeline());
        dto.setCreatedAt(medicationClient.getCreatedAt());
        dto.setLastUpdated(medicationClient.getLastUpdated());
        return dto;
    }

}