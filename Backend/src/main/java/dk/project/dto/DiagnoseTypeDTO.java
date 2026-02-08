package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiagnoseTypeDTO {

    // Attributes
    @JsonProperty("diagnose_type_id")
    private int id;

    @JsonProperty("diagnose_type_name")
    private String name;

}