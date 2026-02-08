package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SideEffectDTO {

    // Attributes
    @JsonProperty("side_effect_id")
    private int id;

    @JsonProperty("side_effect_note")
    private String note;

}