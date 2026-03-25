package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserResponseDTO {

    // Attributes
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("role_id")
    private UUID roleId;
    @JsonProperty("created_at")
    private Timestamp createdAt;

}