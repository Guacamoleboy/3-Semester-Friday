package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MedicationDTO {

    // Attributes
    @JsonProperty("medication_id")
    private int id;

    @JsonProperty("medication_name")
    private String name;

    @JsonProperty("medication_description")
    private String description;

}