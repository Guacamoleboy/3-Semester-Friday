package dk.project.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.sql.Timestamp;

@Data
@JsonIgnoreProperties
public class ApiResponseDTO {

    @JsonProperty("api_name")
    private String name;
    @JsonProperty("last_used")
    private Timestamp lastUsed;
    @JsonProperty("active")
    private boolean active;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("key_id")
    private String keyId;
    @JsonProperty("api_key")
    private String apiKey;

}