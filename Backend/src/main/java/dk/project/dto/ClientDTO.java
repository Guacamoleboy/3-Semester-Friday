package dk.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class ClientDTO {

    // Attributes
    @JsonProperty("client_id")
    private String id;

    @JsonProperty("client_id_ending")
    private int idEnding;

    @JsonProperty("client_created_at")
    private Timestamp createdAt;

    @JsonProperty("client_last_login")
    private Timestamp lastLogin;

}