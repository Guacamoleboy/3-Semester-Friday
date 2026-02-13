package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiagnoseDTO {

    // Attributes
    @JsonProperty("diagnose_id")
    private int id;

    @JsonProperty("diagnose_name")
    private String name;

    @JsonProperty("diagnose_description")
    private String description;

}