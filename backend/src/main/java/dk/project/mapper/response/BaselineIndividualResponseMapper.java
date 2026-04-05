package dk.project.mapper.response;

import dk.project.dto.response.BaselineIndividualResponseDTO;
import dk.project.entity.BaselineIndividual;

public class BaselineIndividualResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static BaselineIndividualResponseDTO toDTO(BaselineIndividual baselineIndividual) {
        BaselineIndividualResponseDTO dto = new BaselineIndividualResponseDTO();
        dto.setId(baselineIndividual.getId());
        dto.setBaselineId(baselineIndividual.getBaseline().getId());
        dto.setQuestionId(baselineIndividual.getQuestion().getId());
        dto.setValue(baselineIndividual.getValue());
        dto.setNote(baselineIndividual.getNote());
        dto.setCreatedAt(baselineIndividual.getCreatedAt());
        return dto;
    }

}