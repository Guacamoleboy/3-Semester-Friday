package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class BaselineIndividualDTO {

    // Attributes
    @JsonProperty("baseline_individual_id")
    private int id;

    @JsonProperty("baseline_individual_baseline_id")
    private Integer baselineId;

    @JsonProperty("baseline_individual_question_id")
    private Integer questionId;

    @JsonProperty("baseline_individual_side_effect_ids")
    private List<Integer> sideEffectIds;

    @JsonProperty("baseline_individual_value")
    private byte value;

    @JsonProperty("baseline_individual_note")
    private String note;

    @JsonProperty("baseline_individual_created_at")
    private LocalDate createdAt;

}