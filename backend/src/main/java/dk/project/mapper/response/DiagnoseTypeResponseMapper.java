package dk.project.mapper.response;

import dk.project.dto.response.DiagnoseTypeResponseDTO;
import dk.project.entity.DiagnoseType;

public class DiagnoseTypeResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static DiagnoseTypeResponseDTO toDTO(DiagnoseType diagnoseType) {
        DiagnoseTypeResponseDTO dto = new DiagnoseTypeResponseDTO();
        dto.setId(diagnoseType.getId());
        dto.setName(diagnoseType.getName());
        return dto;
    }

}