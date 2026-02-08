package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
public class RoleDTO {

    // Attributes
    @JsonProperty("role_id")
    private UUID id;

    @JsonProperty("role_name")
    private String name;

    @JsonProperty("role_description")
    private String description;

}