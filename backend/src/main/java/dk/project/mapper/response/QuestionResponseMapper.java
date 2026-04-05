package dk.project.mapper.response;

import dk.project.dto.response.QuestionResponseDTO;
import dk.project.entity.Question;

public class QuestionResponseMapper {

    // Attributes

    // _________________________________________________________________________________________________________________

    public static QuestionResponseDTO toDTO(Question question) {
        QuestionResponseDTO dto = new QuestionResponseDTO();
        dto.setId(question.getId());
        dto.setQuestionTitle(question.getQuestionTitle());
        dto.setQuestionDescription(question.getQuestionDescription());
        dto.setQuestionValue(question.getQuestionValue());
        return dto;
    }

}