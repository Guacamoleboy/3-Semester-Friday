package dk.project.mapper.response;

import dk.project.dto.response.DiagnoseResponseDTO;
import dk.project.entity.Diagnose;

public class DiagnoseResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static DiagnoseResponseDTO toDTO(Diagnose diagnose) {
        DiagnoseResponseDTO dto = new DiagnoseResponseDTO();
        dto.setId(diagnose.getId());
        dto.setName(diagnose.getName());
        dto.setDescription(diagnose.getDescription());
        dto.setDiagnoseTypeId(diagnose.getDiagnoseType().getId());
        return dto;
    }

}