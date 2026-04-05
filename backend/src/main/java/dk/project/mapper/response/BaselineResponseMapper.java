package dk.project.mapper.response;

import dk.project.dto.response.BaselineResponseDTO;
import dk.project.entity.Baseline;

public class BaselineResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static BaselineResponseDTO toDTO(Baseline baseline) {
        BaselineResponseDTO dto = new BaselineResponseDTO();
        dto.setId(baseline.getId());
        dto.setClientId(baseline.getClient().getId());
        dto.setDiagnoseId(baseline.getDiagnose().getId());
        dto.setCreatedAt(baseline.getCreatedAt());
        dto.setEndDate(baseline.getEndDate());
        dto.setLastUpdated(baseline.getLastUpdated());
        return dto;
    }

}