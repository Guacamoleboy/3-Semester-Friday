package dk.project.mapper.response;

import dk.project.dto.response.DiagnoseClientResponseDTO;
import dk.project.entity.DiagnoseClient;

public class DiagnoseClientResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static DiagnoseClientResponseDTO toDTO(DiagnoseClient diagnoseClient) {
        DiagnoseClientResponseDTO dto = new DiagnoseClientResponseDTO();
        dto.setId(diagnoseClient.getId());
        dto.setDiagnoseId(diagnoseClient.getDiagnose().getId());
        dto.setClientId(diagnoseClient.getClient().getId());
        dto.setCreatedAt(diagnoseClient.getCreatedAt());
        dto.setLastUpdated(diagnoseClient.getLastUpdated());
        return dto;
    }

}