package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionDTO {

    // Attributes
    @JsonProperty("question_id")
    private int id;

    @JsonProperty("question_title")
    private String title;

    @JsonProperty("question_description")
    private String description;

    @JsonProperty("question_value")
    private byte value;

    @JsonProperty("question_diagnosis_id")
    private Integer diagnosisId;

    @JsonProperty("question_medication_id")
    private Integer medicationId;

}