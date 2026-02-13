package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {

    // Attributes
    @JsonProperty("user_id")
    private UUID id;

    @JsonProperty("user_username")
    private String username;

    @JsonProperty("user_email")
    private String email;

    @JsonProperty("user_role_id")
    private UUID roleId;

    @JsonProperty("user_created_at")
    private Timestamp createdAt;

    @JsonProperty("user_last_login")
    private Timestamp lastLogin;

}