package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.sql.Timestamp;

@Data
public class ApiResponseDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("last_used")
    private Timestamp lastUsed;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("created_at")
    private Timestamp createdAt;

}